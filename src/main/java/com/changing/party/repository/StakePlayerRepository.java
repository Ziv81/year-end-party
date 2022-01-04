package com.changing.party.repository;

import com.changing.party.model.StakePlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StakePlayerRepository extends JpaRepository<StakePlayer, Integer> {
}