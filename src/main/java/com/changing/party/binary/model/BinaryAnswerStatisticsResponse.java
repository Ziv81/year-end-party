package com.changing.party.binary.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BinaryAnswerStatisticsResponse {
    private final List<BinaryAnswerStatisticsDto> result;
}
