package com.changing.party.common.exception;

import com.changing.party.dto.MissionQuestionConfigDTO;

public class MissionTypeNotMappingException extends Exception {
    public MissionTypeNotMappingException(MissionQuestionConfigDTO.MissionType exceptType, MissionQuestionConfigDTO.MissionType actualType) {
        super(String.format("Except type {},Actual type {}", exceptType.toString(), actualType.toString()));
    }
}
