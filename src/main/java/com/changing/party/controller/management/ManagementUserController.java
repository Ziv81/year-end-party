package com.changing.party.controller.management;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.ServerConstant;
import com.changing.party.response.GetUserNameListResponse;
import com.changing.party.response.Response;
import com.changing.party.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/rest/management")
@EnableWebMvc
@Log4j2
public class ManagementUserController {
    private UserService userService;

    public ManagementUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/point/reset")
    public Response clearAllStakeData() {
        userService.resetUserPoint();
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .build();
    }

    @GetMapping("/users")
    public Response getUserList() {
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(GetUserNameListResponse.builder().result(GlobalVariable.getGlobalVariableService().getUserNameList()).build())
                .build();
    }

}
