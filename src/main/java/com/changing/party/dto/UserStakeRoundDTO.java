package com.changing.party.dto;

import com.changing.party.common.enums.StakeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStakeRoundDTO {
    private Integer stakeId;
    private StakeStatus status;
    private String title;
    private List<UserStakePlayerDTO> player;
    private Date createTime;
    private Integer winner;
    private Integer winPoint;
    private Integer beforePoint;
}
