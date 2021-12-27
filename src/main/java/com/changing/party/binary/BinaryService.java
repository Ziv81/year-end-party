package com.changing.party.binary;

import com.changing.party.binary.model.*;
import com.changing.party.constant.GlobalVariable;
import com.changing.party.exception.AnswerBinaryNotOpenException;
import com.changing.party.user.UserRepository;
import com.changing.party.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
@Transactional
public class BinaryService {


    @Getter
    @AllArgsConstructor
    public enum BinaryAnswerStatus {
        OPEN(1),
        CLOSE(2);
        int status;
    }

    @Getter
    @AllArgsConstructor
    public enum BinaryChooseOption {
        YES(1),
        NO(2),
        SKIP(3);
        int choose;
    }

    /**
     * 贏得二位元少數決時，單題給予的獎勵
     */
    private static int binaryWinnerScore = 50;

    public static BinaryAnswerStatus binaryAnswerStatus = BinaryAnswerStatus.OPEN;

    BinaryAnswerRepository binaryAnswerRepository;
    BinaryAnswerDetailRepository binaryAnswerDetailRepository;
    BinaryAnswerStatisticsRepository binaryAnswerStatisticsRepository;
    UserRepository userRepository;


    /**
     * 回傳使用者答題狀況
     * <p>
     * 除了 {@link #binaryAnswerStatus} 為不開方且使用者有作答以外，
     * 其他皆有固定靜態回傳內容。
     *
     * @return
     */
    public BinaryAnswerList getBinaryAnswerList() {
        Optional<BinaryAnswerModel> binaryAnswerModelOptional = binaryAnswerRepository.findByAnsweredUser_UserId(10);
        //使用者已經填寫完成
        if (binaryAnswerModelOptional.isPresent()) {
            if (binaryAnswerStatus.equals(BinaryAnswerStatus.CLOSE)) {
                // 不開放作答，且使用者有於開放期間作答
                return BinaryAnswerList.getBinaryAnswerList(binaryAnswerModelOptional.get());
            } else {
                // 開放作答，使用者已經答題，回傳待結算物件
                return BinaryAnswerList.BINARY_OPEN_AND_USER_ANSWERED;
            }
        } else {
            if (binaryAnswerStatus.equals(BinaryAnswerStatus.OPEN)) {
                // 開放作答，使用者尚未完成回答題目，快速回傳題目陣列
                return BinaryAnswerList.BINARY_OPEN_AND_USER_NOT_ANSWERED;
            } else {
                // 不開放作答，且使用者未於開放期間完成作答
                return BinaryAnswerList.BINARY_CLOSE_AND_USER_NOT_ANSWERED;
            }
        }
    }

    /**
     * 使用者回答二位元題目
     *
     * @param answerBinary
     */
    public void answerBinaryQuestion(AnswerBinary answerBinary) throws AnswerBinaryNotOpenException {

        // 檢查是否允許答題
        if (binaryAnswerStatus != BinaryAnswerStatus.OPEN) {
            throw new AnswerBinaryNotOpenException();
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userRepository.findByEnglishNameIgnoreCase(usernamePasswordAuthenticationToken.getName())
                .orElseThrow(() -> new UsernameNotFoundException(usernamePasswordAuthenticationToken.getName()));
        BinaryAnswerModel binaryAnswerModel = binaryAnswerRepository.save(BinaryAnswerModel.builder()
                .answeredUser(user)
                .answeredTime(new Date())
                .build());
        for (int i = 0; i < answerBinary.getChoose().size(); i++) {
            binaryAnswerDetailRepository.save(
                    BinaryAnswerDetailModel.builder()
                            .questionId(i + 1)
                            .choose(answerBinary.getChoose().get(i))
                            .binaryAnswerId(binaryAnswerModel)
                            .build());
        }
    }

    /**
     * 結算各題目分數，並在最後統一將未選擇或各題屬於多數的回答分數設定為0
     */
    public void squareUpScore() {
        GlobalVariable.BINARY_QUESTION_LIST.forEach(
                binaryQuestion -> {
                    int chooseYes = binaryAnswerDetailRepository.countByQuestionIdAndChoose(binaryQuestion.getQuestionId(), BinaryChooseOption.YES.getChoose());
                    int chooseNo = binaryAnswerDetailRepository.countByQuestionIdAndChoose(binaryQuestion.getQuestionId(), BinaryChooseOption.NO.getChoose());
                    int chooseSkip = binaryAnswerDetailRepository.countByQuestionIdAndChoose(binaryQuestion.getQuestionId(), BinaryChooseOption.SKIP.getChoose());
                    squareUpScoreByQuestionId(binaryQuestion.getQuestionId(), chooseYes == chooseNo, chooseYes < chooseNo);
                    updateBinaryAnswerStatistics(binaryQuestion.getQuestionId(), chooseYes, chooseNo, chooseSkip);
                }
        );
        binaryAnswerDetailRepository.updateIsNullScoreToZero();
    }

    /**
     * 結算各題目的結果，並將單題贏得的分數寫入資料庫
     *
     * @param questionId
     * @param allWin
     * @param chooseYesWin
     */
    private void squareUpScoreByQuestionId(Integer questionId, boolean allWin, boolean chooseYesWin) {
        if (allWin) {
            /**
             *  當選擇 {@link BinaryChooseOption#YES} 與 {@link BinaryChooseOption#NO} 數量相同時兩邊皆勝利除了未選擇的人
             */
            binaryAnswerDetailRepository.updateScoreByQuestionIdExceptNoChoose(binaryWinnerScore, questionId);
        } else {
            if (chooseYesWin) {
                // 選擇 Yes 的人較少
                binaryAnswerDetailRepository.updateScoreByQuestionIdAndChoose(binaryWinnerScore, questionId, BinaryChooseOption.YES.getChoose());
            } else {
                // 選擇 No 的人較少
                binaryAnswerDetailRepository.updateScoreByQuestionIdAndChoose(binaryWinnerScore, questionId, BinaryChooseOption.NO.getChoose());
            }
        }
    }

    private void updateBinaryAnswerStatistics(Integer questionId, int chooseYes, int chooseNo, int chooseSkip) {
        binaryAnswerStatisticsRepository.save(BinaryAnswerStatistics.builder()
                .questionId(questionId)
                .chooseYes(chooseYes)
                .chooseNo(chooseNo)
                .chooseSkip(chooseSkip)
                .build());
    }

    /**
     * 僅供測試時使用，清空使用者二位元查詢答題結果與後台管理網站統計結果
     */
    public void clearAllBinaryAnsweredData() {
        binaryAnswerDetailRepository.deleteAll();
        binaryAnswerRepository.deleteAll();
        binaryAnswerStatisticsRepository.deleteAll();
    }
}
