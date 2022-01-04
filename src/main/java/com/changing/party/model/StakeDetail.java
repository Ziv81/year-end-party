package com.changing.party.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stake_detail", indexes = {
        @Index(name = "idx_stakedetail", columnList = "user_model_user_id"),
        @Index(name = "idx_stakedetail_user_id_stake_id", columnList = "user_model_user_id, stake_id")
})
@Getter
@Setter
@RequiredArgsConstructor
public class StakeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "stake_id")
    private Stake stake;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_model_user_id")
    private UserModel userModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stake_player_id")
    private StakePlayer stakePlayer;

    @Column(name = "stakePoint")
    private Integer stakePoint;

    @Temporal(TemporalType.DATE)
    @Column(name = "stake_time")
    private Date stakeTime;
}