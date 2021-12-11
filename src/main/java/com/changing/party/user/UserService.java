package com.changing.party.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserById(long id);

    List<User> getUserByPointSortDesc(int page, int size);
}
