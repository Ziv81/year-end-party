package com.changing.party.binary.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BinaryAnswerStatisticsDto implements Serializable {
    private Integer questionId;
    private Integer chooseYes;
    private Integer chooseNo;
    private Integer chooseSkip;

    public static BinaryAnswerStatisticsDto getBinaryAnswerStatisticsDto(BinaryAnswerStatisticsModel binaryAnswerStatistics) {
        return BinaryAnswerStatisticsDto.builder()
                .questionId(binaryAnswerStatistics.getQuestionId())
                .chooseYes(binaryAnswerStatistics.getChooseYes())
                .chooseNo(binaryAnswerStatistics.getChooseNo())
                .chooseSkip(binaryAnswerStatistics.getChooseSkip())
                .build();
    }
}
