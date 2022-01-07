package com.changing.party.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserCheckInRequest {
    @Min(1)
    int userId;
}
