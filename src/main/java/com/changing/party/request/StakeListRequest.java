package com.changing.party.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Valid
public class StakeListRequest {
    @Size(min = 1)
    List<StakeRequest> stakes;
}
