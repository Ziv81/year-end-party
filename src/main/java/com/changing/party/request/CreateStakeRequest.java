package com.changing.party.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateStakeRequest {
    @NotBlank
    String title;
    @Size(min = 2)
    List<StakePlayerRequest> player;
}
