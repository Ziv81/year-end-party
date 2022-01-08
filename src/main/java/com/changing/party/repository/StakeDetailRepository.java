package com.changing.party.repository;

import com.changing.party.model.StakeDetail;
import com.changing.party.model.StakeOnlyBetPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StakeDetailRepository extends JpaRepository<StakeDetail, Integer> {
    Set<StakeDetail> findByUserModel_UserIdAndStake_Id(Integer userId, Integer id);

    Set<StakeDetail> findByUserModel_UserIdOrderByIdAsc(Integer userId);

    Set<StakeOnlyBetPoint> findByStake_IdAndStakePlayer_PlayerIdIsNot(Integer id, Integer playerId);

    Set<StakeDetail> findByStake_IdAndStakePlayer_PlayerId(Integer id, Integer playerId);
}