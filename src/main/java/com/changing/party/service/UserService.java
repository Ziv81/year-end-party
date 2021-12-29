package com.changing.party.service;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.exception.GetUserRankException;
import com.changing.party.common.exception.UserIdNotFoundException;
import com.changing.party.dto.UserLeaderBoardDTO;
import com.changing.party.model.LoginUser;
import com.changing.party.model.OnlyPointModel;
import com.changing.party.model.UserModel;
import com.changing.party.repository.UserRepository;
import com.changing.party.request.UploadUserRequest;
import com.changing.party.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Log4j2
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponse getUserById(int id) {
        UserModel userModel = Optional.ofNullable(userRepository.findByUserId(id))
                .orElseThrow(() -> new UserIdNotFoundException(id));
        return UserResponse.getUserModel(userModel, getUserRank(userModel.getUserPoint()));
    }

    public UserLeaderBoardDTO getUserLeaderBoard(int size) {
        return UserLeaderBoardDTO.getUserLeaderBoardModel(userRepository.findAllByOrderByUserPoint(Pageable.ofSize(size * 2)), size);
    }

    public void createUsers(List<UploadUserRequest> users,
                            AtomicReference<Integer> createUser,
                            AtomicReference<Integer> editUser,
                            boolean autoUpdate) {
        String passwordHash = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        users.forEach(user -> {
            Optional<UserModel> existUserOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
            List<String> tmp = GlobalVariable.ADMIN_USER_LIST;
            int isAdmin = GlobalVariable.ADMIN_USER_LIST.contains(user.getEmail().toLowerCase(Locale.ROOT)) ? 1 : 0;
            if (!existUserOptional.isPresent()) {
                userRepository.save(UserModel.builder()
                        .password(passwordEncoder.encode(passwordHash))
                        .chineseName(user.getChineseName())
                        .englishName(user.getEnglishName())
                        .department(String.format("%s %s", user.getDepartment(), user.getGroup()))
                        .jobTitle(user.getJobTitle())
                        .email(user.getEmail())
                        .createDate(new Date())
                        .userPoint(0)
                        .isAdmin(isAdmin)
                        .build());
                createUser.getAndSet(createUser.get() + 1);
            } else if (autoUpdate) {
                UserModel existUser = existUserOptional.get();
                existUser.setChineseName(user.getChineseName());
                existUser.setEnglishName(user.getEnglishName());
                existUser.setDepartment(String.format("%s %s", user.getDepartment(), user.getGroup()));
                existUser.setJobTitle(user.getJobTitle());
                existUser.setPassword(passwordEncoder.encode(passwordHash));
                existUser.setIsAdmin(isAdmin);
                userRepository.save(existUser);
                editUser.getAndSet(editUser.get() + 1);
            }
        });
    }

    /**
     * 僅可使用於系統登入
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEnglishNameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database."));
        user.setLastLogin(new Date());
        userRepository.save(user);
        return LoginUser.getLoginUser(user, getUserRank(user.getUserPoint()));
    }

    private int getUserRank(Integer userPoint) {
        List<OnlyPointModel> userPointList = userRepository.findAllByOrderByUserPointDesc();
        for (int i = 0; i < userPointList.size(); i++) {
            if (userPointList.get(i).getUserPoint() == userPoint)
                return i + 1;
        }
        throw new GetUserRankException(userPointList, userPoint);
    }

    public UserModel getUserModelFromSecurityContext() {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userRepository.findByEnglishNameIgnoreCase(usernamePasswordAuthenticationToken.getName())
                .orElseThrow(() -> new UsernameNotFoundException(usernamePasswordAuthenticationToken.getName()));
        return user;
    }
}