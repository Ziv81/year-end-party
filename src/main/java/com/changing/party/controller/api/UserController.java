package com.changing.party.controller.api;

import com.changing.party.common.ServerConstant;
import com.changing.party.common.exception.UploadUserIsEmptyException;
import com.changing.party.response.Response;
import com.changing.party.request.UploadUsersRequest;
import com.changing.party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("rest/api/user")
@EnableWebMvc
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Response findUserById(@PathVariable @Size(max = 200) int id) {
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(userService.getUserById(id))
                .build();
    }

    @GetMapping("/leaderBoard")
    public Response getUserLeaderBoard() {
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(userService.getUserLeaderBoard())
                .build();
    }

    @PostMapping(value = "/uploadUsers",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Response uploadUsers(@RequestBody UploadUsersRequest uploadUsers,
                                @RequestParam(name = "autoUpdate") boolean autoUpdate) {
        AtomicReference<Integer> createUser = new AtomicReference<>(0);
        AtomicReference<Integer> editUser = new AtomicReference<>(0);
        userService.createUsers(
                Optional.ofNullable(uploadUsers.getUsers())
                        .orElseThrow(() -> new UploadUserIsEmptyException()),
                createUser,
                editUser,
                autoUpdate);
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(String.format("Success import user %s, edit user %s.", createUser.get(), editUser.get()))
                .build();
    }
}
