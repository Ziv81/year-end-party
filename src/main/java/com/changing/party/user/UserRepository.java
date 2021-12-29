package com.changing.party.user;

import com.changing.party.user.model.OnlyNameAndPointModel;
import com.changing.party.user.model.OnlyPointModel;
import com.changing.party.user.model.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    List<OnlyNameAndPointModel> findAllByOrderByUserPoint(Pageable pageable);

    UserModel findByEmail(String email);

    UserModel findByUserId(int userId);

    Optional<UserModel> findByEnglishNameIgnoreCase(String englishName);

    List<OnlyPointModel> findAllByOrderByUserPointDesc();
}
