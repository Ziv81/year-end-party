package com.changing.party.response;

import com.changing.party.dto.MissionAnswerDTO;
import lombok.Builder;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Builder
@Getter
public class PendingMissionImageResponse {
    Integer missionId;
    Integer userId;
    List<String> imageUrl;

    public static PendingMissionImageResponse getPendingMissionImageResponse(MissionAnswerDTO missionAnswerDTO) {
        return PendingMissionImageResponse.builder()
                .missionId(missionAnswerDTO.getMissionId())
                .userId(missionAnswerDTO.getUserModel().getUserId())
                .imageUrl(getImageUrlByAnswerContent(missionAnswerDTO.getAnswerContent()))
                .build();
    }

    private static List<String> getImageUrlByAnswerContent(String base64AnswerContent) {
        String decode = new String(Base64.getDecoder().decode(base64AnswerContent), StandardCharsets.UTF_8);
        List<String> results = new ArrayList<>();
        Arrays.stream(decode.split(",")).forEach(x -> results.add(String.format("/management/mission/image/%s", x)));
        return results;
    }
}
