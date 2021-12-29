package com.changing.party.response;

import com.changing.party.dto.BinaryAnswerStatisticsDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BinaryAnswerStatisticsResponse {
    private final List<BinaryAnswerStatisticsDTO> result;
}
