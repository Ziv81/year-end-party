package com.changing.party.dto;

import com.changing.party.model.StakeDetail;
import com.changing.party.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StakeDetailDTO {
    private Integer id;
    private StakeDTO stake;
    private UserModel userModel;
    private StakePlayerDTO stakePlayer;
    private Integer stakePoint;
    private Date stakeTime;

    public static StakeDetailDTO getStakeDetailDTO(StakeDetail stakeDetail, StakeDTO stakeDTO, StakePlayerDTO stakePlayerDTO) {
        return new StakeDetailDTO(stakeDetail.getId(),
                stakeDTO,
                stakeDetail.getUserModel(),
                stakePlayerDTO,
                stakeDetail.getStakePoint(),
                stakeDetail.getStakeTime());
    }
}
