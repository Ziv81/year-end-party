package com.changing.party.service;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.exception.AnswerBinaryNotOpenException;
import com.changing.party.dto.BinaryAnswerListDTO;
import com.changing.party.dto.BinaryAnswerStatisticsDTO;
import com.changing.party.model.BinaryAnswerDetailModel;
import com.changing.party.model.BinaryAnswerModel;
import com.changing.party.model.BinaryAnswerStatisticsModel;
import com.changing.party.repository.BinaryAnswerDetailRepository;
import com.changing.party.repository.BinaryAnswerRepository;
import com.changing.party.repository.BinaryAnswerStatisticsRepository;
import com.changing.party.request.AnswerBinaryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
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
    @Value("${year-end-party.binary.winner.score}")
    private int binaryWinnerScore;

    private static BinaryAnswerStatus binaryAnswerStatus = BinaryAnswerStatus.OPEN;

    BinaryAnswerRepository binaryAnswerRepository;
    BinaryAnswerDetailRepository binaryAnswerDetailRepository;
    BinaryAnswerStatisticsRepository binaryAnswerStatisticsRepository;
    UserService userService;

    public BinaryService(BinaryAnswerRepository binaryAnswerRepository, BinaryAnswerDetailRepository binaryAnswerDetailRepository, BinaryAnswerStatisticsRepository binaryAnswerStatisticsRepository, UserService userService) {
        this.binaryAnswerRepository = binaryAnswerRepository;
        this.binaryAnswerDetailRepository = binaryAnswerDetailRepository;
        this.binaryAnswerStatisticsRepository = binaryAnswerStatisticsRepository;
        this.userService = userService;
    }

    /**
     * 回傳使用者答題狀況
     * <p>
     * 除了 {@link #binaryAnswerStatus} 為不開方且使用者有作答以外，
     * 其他皆有固定靜態回傳內容。
     *
     * @return
     */
    public BinaryAnswerListDTO getBinaryAnswerList() {
        Optional<BinaryAnswerModel> binaryAnswerModelOptional =
                binaryAnswerRepository.findByAnsweredUser_UserId(userService.getUserModelFromSecurityContext().getUserId());
        //使用者已經填寫完成
        if (binaryAnswerModelOptional.isPresent()) {
            if (binaryAnswerStatus.equals(BinaryAnswerStatus.CLOSE)) {
                // 不開放作答，且使用者有於開放期間作答
                return BinaryAnswerListDTO.getBinaryAnswerList(binaryAnswerModelOptional.get());
            } else {
                // 開放作答，使用者已經答題，回傳待結算物件
                return BinaryAnswerListDTO.BINARY_OPEN_AND_USER_ANSWERED;
            }
        } else {
            if (binaryAnswerStatus.equals(BinaryAnswerStatus.OPEN)) {
                // 開放作答，使用者尚未完成回答題目，快速回傳題目陣列
                return BinaryAnswerListDTO.BINARY_OPEN_AND_USER_NOT_ANSWERED;
            } else {
                // 不開放作答，且使用者未於開放期間完成作答
                return BinaryAnswerListDTO.BINARY_CLOSE_AND_USER_NOT_ANSWERED;
            }
        }
    }

    /**
     * 使用者回答二位元題目
     *
     * @param answerBinary
     */
    public void answerBinaryQuestion(AnswerBinaryRequest answerBinary) throws AnswerBinaryNotOpenException {

        // 檢查是否允許答題
        if (binaryAnswerStatus != BinaryAnswerStatus.OPEN) {
            throw new AnswerBinaryNotOpenException();
        }

        BinaryAnswerModel binaryAnswerModel = binaryAnswerRepository.save(BinaryAnswerModel.builder()
                .answeredUser(userService.getUserModelFromSecurityContext())
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
     * 根據結算完的結果，將使用者的分數進行更新
     */
    public void updateUserScoreByBinarySquareUp() {
        binaryAnswerRepository.findAll().forEach(
                binaryAnswerModel ->
                        userService.updateUserPoint(binaryAnswerModel
                                .getBinaryAnswerDetails()
                                .stream()
                                .mapToInt(BinaryAnswerDetailModel::getScore)
                                .sum())

        );
    }

    /**
     * 結算各題目分數，並在最後統一將未選擇或各題屬於多數的回答分數設定為0
     */
    public void squareUpScore() {
        GlobalVariable.getGlobalVariableService().getBINARY_QUESTION_LIST().forEach(
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

    /**
     * 儲存二位元統計結果，供後續管理網站使用。
     *
     * @param questionId
     * @param chooseYes
     * @param chooseNo
     * @param chooseSkip
     */
    private void updateBinaryAnswerStatistics(Integer questionId, int chooseYes, int chooseNo, int chooseSkip) {
        binaryAnswerStatisticsRepository.save(BinaryAnswerStatisticsModel.builder()
                .questionId(questionId)
                .chooseYes(chooseYes)
                .chooseNo(chooseNo)
                .chooseSkip(chooseSkip)
                .build());
    }

    public List<BinaryAnswerStatisticsDTO> getBinaryAnswerStatisticsData() {
        List<BinaryAnswerStatisticsModel> binaryAnswerStatisticsModels = binaryAnswerStatisticsRepository.findAll();
        List<BinaryAnswerStatisticsDTO> binaryAnswerStatisticsDtos = new ArrayList<>();
        if (!binaryAnswerStatisticsModels.isEmpty()) {
            binaryAnswerStatisticsModels.forEach(
                    x -> binaryAnswerStatisticsDtos.add(BinaryAnswerStatisticsDTO.getBinaryAnswerStatisticsDto(x))
            );
        } else {
            GlobalVariable.getGlobalVariableService().getBINARY_QUESTION_LIST().forEach(
                    x -> binaryAnswerStatisticsDtos.add(BinaryAnswerStatisticsDTO
                            .builder()
                            .questionId(x.getQuestionId())
                            .chooseYes(0)
                            .chooseNo(0)
                            .chooseSkip(0)
                            .build())
            );
        }

        return binaryAnswerStatisticsDtos;
    }

    /**
     * 僅供測試時使用，清空使用者二位元查詢答題結果與後台管理網站統計結果
     */
    public void clearAllBinaryAnsweredData() {
        binaryAnswerDetailRepository.deleteAll();
        binaryAnswerRepository.deleteAll();
        binaryAnswerStatisticsRepository.deleteAll();
    }

    public static BinaryAnswerStatus getBinaryAnswerStatus() {
        return binaryAnswerStatus;
    }

    public static void setBinaryAnswerStatus(BinaryAnswerStatus binaryAnswerStatus) {
        BinaryService.binaryAnswerStatus = binaryAnswerStatus;
    }
}
