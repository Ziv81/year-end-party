package com.changing.party.response;

import com.changing.party.dto.MissionImageModelDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PendingMissionImageResponse {
    Integer missionId;
    Integer userId;
    String imageUrl;

    public static PendingMissionImageResponse getPendingMissionImageResponse(MissionImageModelDTO missionImageModelDTO) {
        return PendingMissionImageResponse.builder()
                .missionId(missionImageModelDTO.getMissionAnswerModel().getMissionId())
                .userId(missionImageModelDTO.getMissionAnswerModel().getUserModel().getUserId())
                .imageUrl(String.format("/management/mission/image/%s", missionImageModelDTO.getId()))
                .build();
    }
}
