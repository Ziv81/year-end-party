package com.changing.party.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "stack_player", indexes = {
        @Index(name = "idx_stakeplayer_id", columnList = "id"),
        @Index(name = "idx_stakeplayer_stake_id", columnList = "stake_id")
})
public class StakePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "stake_id")
    private Stake stake;

    @Column(name = "playerName", length = 30)
    private String playerName;

    @Column(name = "playerId")
    private Integer playerId;
}