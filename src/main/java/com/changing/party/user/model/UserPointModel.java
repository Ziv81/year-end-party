package com.changing.party.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserPointModel {
    private Long userId;
    private int userPoint;
    private String englishName;
}
