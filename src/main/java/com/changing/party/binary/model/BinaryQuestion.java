package com.changing.party.binary.model;

import com.changing.party.binary.BinaryService;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BinaryQuestion {
    Integer questionId;
    Integer score;
    Integer choose;

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
