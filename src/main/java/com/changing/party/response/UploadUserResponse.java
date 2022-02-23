package com.changing.party.response;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadUserResponse {
    private Integer userId;
    private String loginName;
    private String password;
}
