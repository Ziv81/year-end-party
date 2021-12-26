package com.changing.party.user;

import com.changing.party.constant.GlobalVariable;
import com.changing.party.exception.UserIdNotFoundException;
import com.changing.party.user.model.UserLeaderBoard;
import com.changing.party.user.model.UserModel;
import com.changing.party.user.model.upload.UploadUser;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Log4j2
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public com.changing.party.user.model.User getUserById(int id) {
        return com.changing.party.user.model.User.getUserModel(Optional.ofNullable(userRepository.findByUserId(id))
                .orElseThrow(() -> new UserIdNotFoundException(id)));
    }

    public UserLeaderBoard getUserLeaderBoard(int size) {
        return UserLeaderBoard.getUserLeaderBoardModel(userRepository.findAllByOrderByUserPoint(Pageable.ofSize(size * 2)), size);
    }

    public void createUsers(List<UploadUser> users,
                            AtomicReference<Integer> createUser,
                            AtomicReference<Integer> editUser,
                            boolean autoUpdate) {
        users.forEach(user -> {
            Optional<UserModel> existUserOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
            List<String> tmp = GlobalVariable.ADMIN_USER_LIST;
            int isAdmin = GlobalVariable.ADMIN_USER_LIST.contains(user.getEmail().toLowerCase(Locale.ROOT)) ? 1 : 0;
            if (!existUserOptional.isPresent()) {
                userRepository.save(UserModel.builder()
                        .password(passwordEncoder.encode("password"))
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
                existUser.setIsAdmin(isAdmin);
                userRepository.save(existUser);
                editUser.getAndSet(editUser.get() + 1);
            }
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEnglishNameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database."));
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("roleName"));
        return new org.springframework.security.core.userdetails.User(user.getEnglishName(), user.getPassword(), authorities);
    }
}
