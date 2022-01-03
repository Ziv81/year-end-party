package com.changing.party.repository;

import com.changing.party.common.AnswerReviewStatus;
import com.changing.party.model.MissionAnswerModel;
import com.changing.party.model.MissionImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MissionImageRepository extends JpaRepository<MissionImageModel, Integer> {
    Optional<MissionImageModel> findById(Integer id);

    @Modifying
    @Query("update MissionImageModel set answerReviewStatus = ?1  where id in ?2")
    Integer updateAnswerReviewStatusByQuestionIdList(AnswerReviewStatus reviewStatus, List<Integer> questionId);
}