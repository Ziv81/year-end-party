package com.changing.party.model;

import com.changing.party.common.enums.LogType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "service_log")
public class ServiceLog {
    public static final int BINARY_ANSWER = 1;
    public static final int BINARY_SQUARE_UP = 2;
    public static final int BINARY_CLEAR_ALL = 3;
    public static final int MISSION_VERIFY_IMAGE = 4;
    public static final int MISSION_ANSWER_IMAGE = 5;
    public static final int MISSION_ANSWER = 6;
    public static final int STAKE_OPEN = 7;
    public static final int STAKE_CLOSE = 8;
    public static final int STAKE_FINISH = 9;
    public static final int STAKE_PLACE_BETS = 10;
    public static final int USER_UPDATE_POINTS = 11;
    public static final int USER_ACCESS_AT_NOT_OPEN = 12;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "logTime")
    private Date logTime;

    @Column(name = "logType")
    private LogType logType;

    @Column(name = "op")
    private int op;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_model_user_id")
    private UserModel userModel;

    @Column(name = "logContent", length = 2048)
    private String logContent;
}