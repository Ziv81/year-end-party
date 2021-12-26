package com.changing.party.binary;

import com.changing.party.binary.model.BinaryAnswerModel;
import com.changing.party.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BinaryAnswerRepository extends JpaRepository<BinaryAnswerModel, Integer> {
    Optional<BinaryAnswerModel> findByAnsweredUser_UserId(Integer userId);

}
