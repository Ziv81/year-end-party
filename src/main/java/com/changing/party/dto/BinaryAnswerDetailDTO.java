package com.changing.party.dto;

import com.changing.party.model.BinaryAnswerDetailModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BinaryAnswerDetailDTO {
    Integer questionId;
    Integer score;
    Integer choose;

    public static BinaryAnswerDetailDTO getBinaryAnswerDetailDTO(BinaryAnswerDetailModel binaryAnswerDetailModel) {
        return BinaryAnswerDetailDTO.builder()
                .questionId(binaryAnswerDetailModel.getQuestionId())
                .score(binaryAnswerDetailModel.getScore())
                .choose(binaryAnswerDetailModel.getChoose())
                .build();
    }
}
