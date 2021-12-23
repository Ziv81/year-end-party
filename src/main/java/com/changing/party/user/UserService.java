package com.changing.party.user;

import com.changing.party.exception.UserIdNotFoundException;
import com.changing.party.user.model.User;
import com.changing.party.user.model.UserLeaderBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return User.getUserModel(Optional.ofNullable(userRepository.getById((int) id))
                .orElseThrow(() -> new UserIdNotFoundException(id)));
    }

    public UserLeaderBoard getUserLeaderBoard(int size) {
        return UserLeaderBoard.getUserLeaderBoardModel(userRepository.findAllByOrderByUserPoint(Pageable.ofSize(size * 2)), size);
    }
}
