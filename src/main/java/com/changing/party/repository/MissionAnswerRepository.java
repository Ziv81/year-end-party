package com.changing.party.repository;

import com.changing.party.common.enums.AnswerReviewStatus;
import com.changing.party.model.MissionAnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface MissionAnswerRepository extends JpaRepository<MissionAnswerModel, Integer> {
    boolean existsByUserModel_UserIdAndMissionId(Integer userId, Integer missionId);

    Set<MissionAnswerModel> findByUserModel_UserIdOrderByMissionIdAsc(Integer userId);

    Optional<MissionAnswerModel> findByUserModel_UserIdAndMissionId(Integer userId, Integer missionId);

    Set<MissionAnswerModel> findByAnswerReviewStatus(AnswerReviewStatus answerReviewStatus);
}