package com.changing.party.repository;

import com.changing.party.model.StakePlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StakePlayerRepository extends JpaRepository<StakePlayer, Integer> {
    List<StakePlayer> findByStake_Id(Integer id);

}