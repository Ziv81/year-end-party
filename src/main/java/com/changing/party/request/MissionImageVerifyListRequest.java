package com.changing.party.request;

import com.changing.party.common.constraint.annotation.ValidateVerifyMissionImageOP;
import lombok.Getter;

@Getter
public class MissionImageVerifyRequest {

    @ValidateVerifyMissionImageOP
    String op;
}
