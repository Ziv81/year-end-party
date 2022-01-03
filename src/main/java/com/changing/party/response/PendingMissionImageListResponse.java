package com.changing.party.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PendingMissionImageListResponse {
    List<PendingMissionImageResponse> result;
}
