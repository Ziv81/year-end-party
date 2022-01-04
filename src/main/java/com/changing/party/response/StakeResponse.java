package com.changing.party.response;

import com.changing.party.dto.StakeDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class StakeResponse {
    Integer stakeId;
    String title;
    Integer status;
    List<StakePlayerResponse> player;

    public static StakeResponse getStakeResponse(StakeDTO stakeDTO) {
        List<StakePlayerResponse> player = new ArrayList<>();
        stakeDTO.getStakePlayer().forEach(x -> player.add(StakePlayerResponse.getStakePlayerResponse(x)));
        return StakeResponse.builder()
                .stakeId(stakeDTO.getId())
                .title(stakeDTO.getTitle())
                .status(stakeDTO.getStatus().getStatus())
                .player(player)
                .build();
    }
}
