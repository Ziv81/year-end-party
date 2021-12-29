package com.changing.party.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetTokenResponse {
    String accessToken;
    String refreshToken;
    int expiresIn;
    String tokenType;
    UserResponse userInfo;
}
