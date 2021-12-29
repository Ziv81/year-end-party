package com.changing.party.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashSet;

@Getter
public class LoginUser extends User {
    String title;
    Integer userId;
    Integer userRank;
    Integer userPoint;
    Integer isCheckIn;
    Integer isAgree;

    public static LoginUser getLoginUser(UserModel user, int userRank) {
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("roleName"));
        return new LoginUser(user.getEnglishName(),
                user.getPassword(),
                authorities,
                user.getJobTitle(),
                user.getUserId(),
                userRank,
                user.getUserPoint(),
                user.getIsCheckIn(),
                user.getIsAgree());
    }

    private LoginUser(String username,
                      String password,
                      Collection<? extends GrantedAuthority> authorities,
                      String title,
                      Integer userId,
                      Integer userRank,
                      Integer userPoint,
                      Integer isCheckIn,
                      Integer isAgree) {
        super(username, password, authorities);
        this.title = title;
        this.userId = userId;
        this.userRank = userRank;
        this.userPoint = userPoint;
        this.isCheckIn = isCheckIn;
        this.isAgree = isAgree;
    }
}
