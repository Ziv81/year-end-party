package com.changing.party.response;

import com.changing.party.dto.StakeDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class StakeListResponse {
    List<StakeResponse> result;

    public static StakeListResponse getStakeListResponse(List<StakeDTO> stakeDTOS) {
        List<StakeResponse> result = new ArrayList<>();
        stakeDTOS.forEach(x -> result.add(StakeResponse.getStakeResponse(x)));
        return StakeListResponse.builder().result(result).build();
    }
}
