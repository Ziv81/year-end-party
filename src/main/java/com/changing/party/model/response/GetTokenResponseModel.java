package com.changing.party.model.response;

import com.changing.party.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetTokenResponseModel {
    String accessToken;
    String refreshToken;
    int expiresIn;
    String tokenType;
    User userInfo;
}
