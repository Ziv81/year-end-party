package com.changing.party.repository;

import com.changing.party.model.BinaryAnswerDetailModel;
import com.changing.party.model.BinaryAnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BinaryAnswerDetailRepository extends JpaRepository<BinaryAnswerDetailModel, Integer> {
    Set<BinaryAnswerDetailModel> findByBinaryAnswerId(BinaryAnswerModel binaryAnswerId);
    int countByQuestionIdAndChoose(Integer questionId, Integer choose);

    @Modifying
    @Query("update BinaryAnswerDetailModel set score = ?1 where questionId = ?2 and choose <> 3")
    Integer updateScoreByQuestionIdExceptNoChoose(int score, int questionId);

    @Modifying
    @Query("update BinaryAnswerDetailModel set score = ?1 where questionId = ?2 and choose = ?3")
    Integer updateScoreByQuestionIdAndChoose(int score, int question, int choose);

    @Modifying
    @Query("update BinaryAnswerDetailModel set score = 0 where score is null")
    Integer updateIsNullScoreToZero();
}
