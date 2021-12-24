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
    Integer userId;
    Integer userPoint;
    String englishName;
}
