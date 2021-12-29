package com.changing.party.request;

import com.changing.party.request.UploadUserRequest;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Builder
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadUsersRequest {
    List<UploadUserRequest> users;
}
