package com.changing.party.dto;

import com.changing.party.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModelDTO {
    private Integer userId;
    private String password;
    private String chineseName;
    private String englishName;
    private String loginName;
    private String department;
    private String jobTitle;
    private String email;
    private Date createDate;
    private Date lastLogin;
    private int userPoint;
    private int isAdmin;
    private Integer isCheckIn;
    private Integer isAgree;
    private Integer rank;

    public static UserModelDTO getUserDTO(UserModel userModel,Integer rank) {
        return new UserModelDTO(userModel.getUserId(),
                userModel.getPassword(),
                userModel.getChineseName(),
                userModel.getEnglishName(),
                userModel.getLoginName(),
                userModel.getDepartment(),
                userModel.getJobTitle(),
                userModel.getEmail(),
                userModel.getCreateDate(),
                userModel.getLastLogin(),
                userModel.getUserPoint(),
                userModel.getIsAdmin(),
                userModel.getIsCheckIn(),
                userModel.getIsAgree(),
                rank);
    }
}
