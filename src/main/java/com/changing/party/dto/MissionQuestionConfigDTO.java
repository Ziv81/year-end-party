package com.changing.party.dto;

import com.changing.party.common.exception.MissionTypeUnknownException;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;

/**
 * 從本機檔案讀取的內容轉存為此物件
 */
@Data
@Builder
public class MissionQuestionConfigDTO {
    public enum MissionType {
        NONE(0),
        IMAGE(1),
        CHOOSE(2),
        TEXT(3);

        @Getter
        private int type;

        MissionType(int type) {
            this.type = type;
        }

        @SneakyThrows
        public static MissionType getMissionType(int type) {
            return Arrays.stream(MissionType.values())
                    .filter(x -> x.getType() == type)
                    .findAny()
                    .orElseThrow(() -> new MissionTypeUnknownException(type));
        }
    }

    private int missionId;
    private MissionType missionType;
    private int missionReward;
    private List<String> missionAnswer;
}
