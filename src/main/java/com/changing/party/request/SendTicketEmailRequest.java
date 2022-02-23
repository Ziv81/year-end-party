package com.changing.party.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Valid
public class SendTicketEmailRequest {
    @Min(1)
    Integer userId;
    @NotBlank
    String password;
    @NotBlank
    String loginName;
}
