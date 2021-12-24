package com.changing.party.user.model.upload;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Builder
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadUsers {
    List<UploadUser> users;
}
