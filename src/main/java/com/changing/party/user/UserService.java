package com.changing.party.user;

import com.changing.party.exception.UserIdNotFoundException;
import com.changing.party.user.model.User;
import com.changing.party.user.model.UserLeaderBoard;
import com.changing.party.user.model.UserModel;
import com.changing.party.user.model.upload.UploadUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return User.getUserModel(Optional.ofNullable(userRepository.findByUserId(id))
                .orElseThrow(() -> new UserIdNotFoundException(id)));
    }

    public UserLeaderBoard getUserLeaderBoard(int size) {
        return UserLeaderBoard.getUserLeaderBoardModel(userRepository.findAllByOrderByUserPoint(Pageable.ofSize(size * 2)), size);
    }

    public void createUsers(List<UploadUser> users, AtomicReference<Integer> createUser, AtomicReference<Integer> editUser) {
        users.forEach(user -> {
            Optional<UserModel> existUserOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
            if (!existUserOptional.isPresent()) {
                userRepository.save(UserModel.builder()
                        .password("password")
                        .chineseName(user.getChineseName())
                        .englishName(user.getEnglishName())
                        .department(String.format("%s %s", user.getDepartment(), user.getGroup()))
                        .jobTitle(user.getJobTitle())
                        .email(user.getEmail())
                        .createDate(new Date())
                        .userPoint(0)
                        .build());
                createUser.getAndSet(createUser.get() + 1);
            } else {
                UserModel existUser = existUserOptional.get();
                existUser.setChineseName(user.getChineseName());
                existUser.setEnglishName(user.getEnglishName());
                existUser.setDepartment(String.format("%s %s", user.getDepartment(), user.getGroup()));
                existUser.setJobTitle(user.getJobTitle());
                userRepository.save(existUser);
                editUser.getAndSet(editUser.get() + 1);
            }
        });
    }
}
