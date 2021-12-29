package com.changing.party.dto;

import com.changing.party.model.BinaryAnswerStatisticsModel;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BinaryAnswerStatisticsDTO implements Serializable {
    private Integer questionId;
    private Integer chooseYes;
    private Integer chooseNo;
    private Integer chooseSkip;

    public static BinaryAnswerStatisticsDTO getBinaryAnswerStatisticsDto(BinaryAnswerStatisticsModel binaryAnswerStatistics) {
        return BinaryAnswerStatisticsDTO.builder()
                .questionId(binaryAnswerStatistics.getQuestionId())
                .chooseYes(binaryAnswerStatistics.getChooseYes())
                .chooseNo(binaryAnswerStatistics.getChooseNo())
                .chooseSkip(binaryAnswerStatistics.getChooseSkip())
                .build();
    }
}
