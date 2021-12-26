package com.changing.party.constant;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ServerConstant {
    public static final int SERVER_SUCCESS_CODE = 0;
    public static final String SERVER_SUCCESS_MESSAGE = "Success";

    public static final int SERVER_FAIL_CODE = 66001;
    public static final String SERVER_FAIL_MESSAGE = "Occur exception";
}
