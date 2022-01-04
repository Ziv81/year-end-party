package com.changing.party.model;

import com.changing.party.common.enums.StakeStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stack", indexes = {
        @Index(name = "idx_stake_id", columnList = "id"),
        @Index(name = "idx_stake_status", columnList = "status")
})
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Stake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status")
    private StakeStatus status;

    @Column(name = "title", length = 32)
    private String title;

    @OneToMany(mappedBy = "stake", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<StakePlayer> stakePlayers = new ArrayList<>();
}