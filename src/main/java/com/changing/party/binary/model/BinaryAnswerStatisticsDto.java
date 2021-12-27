package com.changing.party.binary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BinaryAnswerStatisticsDto implements Serializable {
    private Integer questionId;
    private Integer chooseYes;
    private Integer chooseNo;
    private Integer chooseSkip;

    public static BinaryAnswerStatisticsDto getBinaryAnswerStatisticsDto(BinaryAnswerStatistics binaryAnswerStatistics) {
        return BinaryAnswerStatisticsDto.builder()
                .questionId(binaryAnswerStatistics.getQuestionId())
                .chooseYes(binaryAnswerStatistics.getChooseYes())
                .chooseNo(binaryAnswerStatistics.getChooseNo())
                .chooseSkip(binaryAnswerStatistics.getChooseSkip())
                .build();
    }
}
