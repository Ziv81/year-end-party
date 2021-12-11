package com.changing.party.user;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Integer userId;
    @NotBlank
    String userName;
    @NotBlank
    String title;
    @Min(0)
    int userPoint;
    int userRank;
}
