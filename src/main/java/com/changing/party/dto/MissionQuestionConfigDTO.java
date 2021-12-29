package com.changing.party.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 從本機檔案讀取的內容轉存為此物件
 */
@Data
@Builder
public class MissionQuestionConfigDTO {
    private int missionId;
    private int missionType;
    private List<String> missionAnswer;
}
