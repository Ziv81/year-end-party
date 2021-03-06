package com.changing.party.request;

import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
public class UpdateStakeStatusRequest {
    @Min(1)
    int data;

    String op;
}
