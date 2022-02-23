package com.changing.party.response;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Builder
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadUsersResponse {
    List<UploadUserResponse> users;
}
