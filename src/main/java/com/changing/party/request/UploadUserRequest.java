package com.changing.party.request;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Builder
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadUserRequest {
    private String chineseName;
    private String englishName;
    private String department;
    private String group;
    private String jobTitle;
    private String email;
}
