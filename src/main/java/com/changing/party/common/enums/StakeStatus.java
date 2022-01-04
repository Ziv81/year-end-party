package com.changing.party.common.enums;

import com.changing.party.common.exception.StakeStatusUnknownException;
import lombok.Getter;

import java.util.Arrays;

public enum StakeStatus {
    OPEN(1), CLOSE(2), FINISH(3);
    @Getter
    private final int status;

    StakeStatus(int status) {
        this.status = status;
    }

    public static StakeStatus convert(Integer status) throws StakeStatusUnknownException {
        return Arrays.stream(StakeStatus.values())
                .filter(x -> x.getStatus() == status)
                .findAny()
                .orElseThrow(() -> new StakeStatusUnknownException(status));
    }
}
