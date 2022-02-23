package com.changing.party.service;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.Utils;
import com.changing.party.common.exception.GetUserRankException;
import com.changing.party.common.exception.OnlyCanGetOwnUserInfoException;
import com.changing.party.common.exception.UserAlreadyCheckInException;
import com.changing.party.common.exception.UserIdNotFoundException;
import com.changing.party.dto.UserLeaderBoardDTO;
import com.changing.party.dto.UserModelDTO;
import com.changing.party.model.LoginUser;
import com.changing.party.model.OnlyPointModel;
import com.changing.party.model.UserModel;
import com.changing.party.repository.UserRepository;
import com.changing.party.request.UploadUserRequest;
import com.changing.party.response.UploadUserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Log4j2
@AllArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserModelDTO getCurrentUserDTO(int id) throws OnlyCanGetOwnUserInfoException {
        UserModel userModel = getUserModelFromSecurityContext();
        if (userModel.getUserId() != id)
            throw new OnlyCanGetOwnUserInfoException();
        return UserModelDTO.getUserDTO(userModel, getUserRank(userModel.getUserPoint()));
    }

    public UserModelDTO getUserById(int id) {
        UserModel userModel = getUserModelById(id);
        return UserModelDTO.getUserDTO(userModel, getUserRank(userModel.getUserPoint()));
    }

    public UserLeaderBoardDTO getUserLeaderBoard() {
        return UserLeaderBoardDTO.getUserLeaderBoardModel(userRepository.findByOrderByUserPointDescEnglishNameAsc());
    }


    public List<UploadUserResponse> createUsers(List<UploadUserRequest> users,
                                                AtomicReference<Integer> createUser,
                                                AtomicReference<Integer> editUser,
                                                boolean autoUpdate) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        List<UploadUserResponse> uploadUserResponses = new ArrayList<>();

        users.forEach(user -> {
            Optional<UserModel> existUserOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
            int isAdmin = GlobalVariable.getGlobalVariableService().getADMIN_USER_LIST().contains(user.getEmail().toLowerCase(Locale.ROOT)) ? 1 : 0;
            String loginName = user.getEmail().replace("@changingtec.com", "");
            Integer userId = 0;
            // Read password from request or random password, length 5
            String plainTextPassword = StringUtils.isBlank(user.getPassword()) ? RandomStringUtils.randomAlphanumeric(5) : user.getPassword();
            String sha256Password = Utils.byteToHex(digest.digest(
                    plainTextPassword.getBytes(StandardCharsets.UTF_8))).toLowerCase(Locale.ROOT);

            if (!existUserOptional.isPresent()) {
                UserModel newUser = userRepository.save(UserModel.builder()
                        .password(passwordEncoder.encode(sha256Password))
                        .chineseName(user.getChineseName())
                        .englishName(user.getEnglishName())
                        .department(String.format("%s %s", user.getDepartment(), user.getGroup()))
                        .loginName(loginName)
                        .jobTitle(user.getJobTitle())
                        .email(user.getEmail())
                        .createDate(new Date())
                        .userPoint(0)
                        .isAdmin(isAdmin)
                        .build());
                userId = newUser.getUserId();
                createUser.getAndSet(createUser.get() + 1);
            } else if (autoUpdate) {
                UserModel existUser = existUserOptional.get();
                existUser.setChineseName(user.getChineseName());
                existUser.setEnglishName(user.getEnglishName());
                existUser.setLoginName(loginName);
                existUser.setDepartment(String.format("%s %s", user.getDepartment(), user.getGroup()));
                existUser.setJobTitle(user.getJobTitle());
                existUser.setPassword(passwordEncoder.encode(sha256Password));
                existUser.setIsAdmin(isAdmin);
                userRepository.save(existUser);
                userId = existUser.getUserId();
                editUser.getAndSet(editUser.get() + 1);
            }
            uploadUserResponses.add(UploadUserResponse.builder()
                    .userId(userId)
                    .loginName(loginName)
                    .password(plainTextPassword)
                    .build());
        });
        return uploadUserResponses;
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
        UserModel user = userRepository.findByLoginNameIgnoreCase(username)
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
        if (usernamePasswordAuthenticationToken == null) {
            return null;
        }
        return userRepository.findByLoginNameIgnoreCase(usernamePasswordAuthenticationToken.getName())
                .orElseThrow(() -> new UsernameNotFoundException(usernamePasswordAuthenticationToken.getName()));
    }

    /**
     * 更新使用者分數
     *
     * @param userPoint
     */
    public void updateUserPoint(UserModel user, int userPoint) {
        user.setUserPoint(user.getUserPoint() + userPoint);
        userRepository.save(user);
    }


    public void resetUserPoint() {
        userRepository.resetUserPoint();
    }

    /**
     * 使用者報到
     *
     * @param userId
     */
    public void userCheckIn(int userId) throws UserAlreadyCheckInException {
        UserModel userModel = Optional.ofNullable(userRepository.findByUserId(userId))
                .orElseThrow(() -> new UserIdNotFoundException(userId));
        if (userModel.getIsCheckIn() != null && userModel.getIsCheckIn() == 1) {
            throw new UserAlreadyCheckInException();
        }
        userModel.setIsCheckIn(1);
        userRepository.save(userModel);
    }

    /**
     * 測試用，重設所有使用者報到狀態
     */
    public void resetUserCheckIn() {
        userRepository.resetUserIsCheckIn();
    }

    /**
     * 更新使用者密碼欄位
     *
     * @param userModelDTO
     * @param sha256Password SHA-256 password
     */
    public void updateUserPassword(UserModelDTO userModelDTO, String sha256Password) {
        UserModel userModel = getUserModelById(userModelDTO.getUserId());
        userModel.setPassword(passwordEncoder.encode(sha256Password));
        userRepository.save(userModel);
    }

    private UserModel getUserModelById(Integer id) {
        return Optional.ofNullable(userRepository.findByUserId(id))
                .orElseThrow(() -> new UserIdNotFoundException(id));
    }

}
