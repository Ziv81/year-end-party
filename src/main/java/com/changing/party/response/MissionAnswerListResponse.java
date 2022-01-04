package com.changing.party.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MissionAnswerListResponse {
    List<MissionAnswerResponse> result;
    Integer status;
}
