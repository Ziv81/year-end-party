package com.changing.party.merge;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPassword {
    private Integer userId;
    private String loginName;
    private String password;
}
