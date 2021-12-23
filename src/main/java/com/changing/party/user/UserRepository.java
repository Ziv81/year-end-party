package com.changing.party.user;

import com.changing.party.user.model.UserModel;
import com.changing.party.user.model.UserPointModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    List<UserPointModel> findAllByOrderByUserPoint(Pageable pageable);
}
