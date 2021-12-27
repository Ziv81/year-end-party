package com.changing.party.user.model;

import com.changing.party.binary.model.BinaryAnswerModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(exclude = "answerList")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
