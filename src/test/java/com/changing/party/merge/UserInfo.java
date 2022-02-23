package com.changing.party.merge;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String chineseName;
    private String englishName;
    private String department;
    private String group;
    private String jobTitle;
    private String email;
    private String password;
}
