package com.changing.party.request;

import com.changing.party.common.constraint.annotation.ValidateAnswerMissionIdExist;
import lombok.Getter;

@Getter
public class MissionImageVerifyRequest {
    @ValidateAnswerMissionIdExist
    Integer missionId;
    Integer userId;
}
