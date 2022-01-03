package com.changing.party.common;

import com.changing.party.common.exception.MissionAnswerStatusUnknownException;
import lombok.Getter;

import java.util.Arrays;

public enum AnswerReviewStatus {
    SUCCESS(1), FAIL(2), REVIEW(3);
    @Getter
    private final int status;

    AnswerReviewStatus(int status) {
        this.status = status;
    }

    public static AnswerReviewStatus convert(Integer status) throws MissionAnswerStatusUnknownException {
        return Arrays.stream(AnswerReviewStatus.values())
                .filter(x -> x.getStatus() == status)
                .findAny()
                .orElseThrow(() -> new MissionAnswerStatusUnknownException(status));
    }
}
