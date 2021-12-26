package com.changing.party.binary;

import com.changing.party.binary.model.AnswerBinary;
import com.changing.party.binary.model.BinaryAnswerList;
import com.changing.party.binary.model.BinaryAnswerModel;
import com.changing.party.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BinaryService {


    @Getter
    @AllArgsConstructor
    public enum BinaryAnswerStatus {
        OPEN(1),
        CLOSE(2);
        int status;
    }

    public static BinaryAnswerStatus binaryAnswerStatus = BinaryAnswerStatus.OPEN;

    BinaryAnswerRepository binaryAnswerRepository;

    @Autowired
    public BinaryService(BinaryAnswerRepository binaryAnswerRepository) {
        this.binaryAnswerRepository = binaryAnswerRepository;
    }

    /**
     * 回傳使用者答題狀況
     *
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
     * @param answerBinary
     */
    public void answerBinaryQuestion(AnswerBinary answerBinary) {

    }
}
