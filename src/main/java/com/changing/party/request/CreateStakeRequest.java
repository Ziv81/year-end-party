package com.changing.party.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateStakeRequest {
    String title;
    List<StakePlayerRequest> player;
}
