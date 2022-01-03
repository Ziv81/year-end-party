package com.changing.party.request;

import com.changing.party.common.constraint.annotation.ValidateVerifyMissionImageOP;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionImageVerifyListRequest {
    List<MissionImageVerifyRequest> data;

    @ValidateVerifyMissionImageOP
    String op;
}
