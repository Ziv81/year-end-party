package com.changing.party.repository;

import com.changing.party.model.BinaryAnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BinaryAnswerRepository extends JpaRepository<BinaryAnswerModel, Integer> {
    Optional<BinaryAnswerModel> findByAnsweredUser_UserId(Integer userId);

}
