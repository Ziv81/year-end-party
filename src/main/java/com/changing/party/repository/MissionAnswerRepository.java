package com.changing.party.repository;

import com.changing.party.model.MissionAnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionAnswerRepository extends JpaRepository<MissionAnswerModel, Integer> {
    boolean existsByUserModel_UserIdAndMissionId(Integer userId, Integer missionId);
}