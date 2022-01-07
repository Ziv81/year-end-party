package com.changing.party.repository;

import com.changing.party.model.OnlyNameAndPointModel;
import com.changing.party.model.OnlyNameModel;
import com.changing.party.model.OnlyPointModel;
import com.changing.party.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    List<OnlyNameAndPointModel> findByOrderByUserPointDescEnglishNameAsc();

    UserModel findByEmail(String email);

    UserModel findByUserId(int userId);

    Optional<UserModel> findByLoginNameIgnoreCase(String loginName);

    List<OnlyPointModel> findAllByOrderByUserPointDesc();

    List<UserModel> findByOrderByEmailAsc();


    @Modifying
    @Query("update UserModel set isCheckIn = 0")
    Integer resetUserIsCheckIn();

    @Modifying
    @Query("update UserModel set userPoint = 0")
    Integer resetUserPoint();
}
