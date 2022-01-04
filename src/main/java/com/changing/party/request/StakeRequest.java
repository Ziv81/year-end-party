package com.changing.party.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Valid
public class StakeRequest {
    @Min(1)
    Integer playerId;
    @Min(0)
    Integer point;
}
