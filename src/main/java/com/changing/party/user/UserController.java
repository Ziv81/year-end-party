package com.changing.party.user;

import com.changing.party.constant.ServerConstant;
import com.changing.party.exception.UploadUserIsEmptyException;
import com.changing.party.model.response.ResponseModel;
import com.changing.party.user.model.upload.UploadUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/user")
@EnableWebMvc
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseModel findUserById(@PathVariable int id) {
        return ResponseModel.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(userService.getUserById(id))
                .build();
    }

    @GetMapping("/leaderBoard")
    public ResponseModel getUserLeaderBoard() {
        return ResponseModel.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(userService.getUserLeaderBoard(10))
                .build();
    }

    @PostMapping(value = "/uploadUsers",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseModel uploadUsers(@RequestBody UploadUsers uploadUsers,
                                     @RequestParam(name = "autoUpdate") boolean autoUpdate) {
        AtomicReference<Integer> createUser = new AtomicReference<>(0);
        AtomicReference<Integer> editUser = new AtomicReference<>(0);
        userService.createUsers(
                Optional.ofNullable(uploadUsers.getUsers())
                        .orElseThrow(() -> new UploadUserIsEmptyException()),
                createUser,
                editUser,
                autoUpdate);
        return ResponseModel.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(String.format("Success import user %s, edit user %s.", createUser.get(), editUser.get()))
                .build();
    }
}
