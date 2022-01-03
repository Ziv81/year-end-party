package com.changing.party.model;

import com.changing.party.model.BinaryAnswerModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "year_end_party_user", indexes = {
        @Index(name = "idx_usermodel_englishname", columnList = "englishName"),
        @Index(name = "idx_usermodel_userid", columnList = "userId")
})
@Data
@EqualsAndHashCode(exclude = "answerList")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "chineseName", nullable = false)
    private String chineseName;

    @Column(name = "englishName", nullable = false)
    private String englishName;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "userPoint", nullable = false)
    private int userPoint;

    @Column(name = "isAdmin", nullable = false)
    private int isAdmin;

    @OneToOne(mappedBy = "answeredUser")
    private BinaryAnswerModel answerList;

    @Column(name = "isCheckIn")
    private Integer isCheckIn;

    @Column(name = "isAgree")
    private Integer isAgree;
}
