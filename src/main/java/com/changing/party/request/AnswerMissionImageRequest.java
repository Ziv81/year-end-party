package com.changing.party.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AnswerMissionImageRequest {

    // base64 image list
    @Size(min = 1)
    List<String> answer;
}
