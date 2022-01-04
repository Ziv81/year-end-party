package com.changing.party.repository;

import com.changing.party.common.enums.StakeStatus;
import com.changing.party.model.Stake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StakeRepository extends JpaRepository<Stake, Integer> {
    boolean existsByStatus(StakeStatus status);

    @Override
    Optional<Stake> findById(Integer integer);

    Optional<Stake> findByStatus(StakeStatus status);
}