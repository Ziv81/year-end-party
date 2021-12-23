package com.changing.party.binary.model;

import com.changing.party.binary.BinaryService;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BinaryQuestion {
    int questionId;
    int score;
    int choose;

    public static BinaryQuestion getBinaryQuestion(BinaryQuestionModel binaryQuestionModel) {
        if (BinaryService.binaryStatus.equals(BinaryService.AnswerStatus.OPEN)) {
            return BinaryQuestion.builder().questionId(binaryQuestionModel.getQuestionId()).build();
        } else {
            return BinaryQuestion.builder()
                    .questionId(binaryQuestionModel.getQuestionId())
                    .score(binaryQuestionModel.getScore())
                    .choose(binaryQuestionModel.getChoose())
                    .build();
        }
    }
}
