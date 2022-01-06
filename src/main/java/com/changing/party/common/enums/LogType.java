package com.changing.party.common.enums;

import com.changing.party.common.exception.LogTypeUnknownException;
import lombok.Getter;

import java.util.Arrays;

public enum LogType {
    USER(1), BINARY(2), MISSION(3), STAKE(4);
    @Getter
    private final int code;

    LogType(int code) {
        this.code = code;
    }

    public static LogType convert(Integer code) throws LogTypeUnknownException {
        return Arrays.stream(LogType.values())
                .filter(x -> x.getCode() == code)
                .findAny()
                .orElseThrow(() -> new LogTypeUnknownException());
    }
}
