package com.changing.party.binary.model;

import com.changing.party.binary.BinaryService;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class BinaryQuestionList {
    int status;
    List<BinaryQuestion> result;

    public static BinaryQuestionList getBinaryQuestionList(List<BinaryQuestionModel> questionModels) {
        List<BinaryQuestion> binaryQuestions = new ArrayList<>();
        questionModels.forEach(x -> binaryQuestions.add(BinaryQuestion.getBinaryQuestion(x)));
        int status = BinaryService.binaryStatus.getStatus();

        // 若為開放答題，多判斷使用者是否已經完成答題
        if (BinaryService.binaryStatus.equals(BinaryService.AnswerStatus.OPEN)
                && answered(questionModels)) {
            status = BinaryService.AnswerStatus.WAITING.getStatus();
        }

        if (BinaryService.binaryStatus.equals(BinaryService.AnswerStatus.CLOSE)
                && !answered(questionModels)) {
            return BinaryQuestionList.builder().status(BinaryService.AnswerStatus.CLOSE.getStatus()).build();
        }

        return BinaryQuestionList.builder()
                .result(binaryQuestions)
                .status(status)
                .build();
    }

    private static boolean answered(List<BinaryQuestionModel> binaryQuestionModels) {
        return binaryQuestionModels.get(0).getChoose() != BinaryQuestionModel.CHOOSE_NOT_CHOOSE;
    }
}
