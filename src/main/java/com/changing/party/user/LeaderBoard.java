package com.changing.party.user;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class LeaderBoard {
    List<User> result;
}
