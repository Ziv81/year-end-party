package com.changing.party.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserModel {
    private Long userId;
    private String password;
    private String chineseName;
    private String englishName;
    private String department;
    private String jobTitle;
    private String email;
    private Date createDate;
    private Date lastLogin;
    private int userPoint;
}
