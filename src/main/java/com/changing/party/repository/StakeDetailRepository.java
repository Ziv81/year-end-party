package com.changing.party.repository;

import com.changing.party.model.StakeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StakeDetailRepository extends JpaRepository<StakeDetail, Integer> {
    Set<StakeDetail> findByUserModel_UserIdAndStake_Id(Integer userId, Integer id);

    Set<StakeDetail> findByUserModel_UserId(Integer userId);
}