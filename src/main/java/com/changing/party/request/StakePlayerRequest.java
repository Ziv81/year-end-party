package com.changing.party.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StakePlayerRequest {
    @Size(min = 1)
    Integer playerId;
    @NotBlank
    String playerName;
}
