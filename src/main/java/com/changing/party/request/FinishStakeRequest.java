package com.changing.party.request;

import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
public class FinishStakeRequest {
    @Min(1)
    int winner;
}
