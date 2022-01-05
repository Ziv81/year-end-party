package com.changing.party.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MissionImageVerifyResponse {
    Integer targetId;
    Integer targetName;
    Integer errorCode;
    String errorMessage;
}
