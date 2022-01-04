package com.changing.party.request;

import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class UpdateStakeStatusRequest {
    @Size(min = 1)
    Integer data;

    String op;
}
