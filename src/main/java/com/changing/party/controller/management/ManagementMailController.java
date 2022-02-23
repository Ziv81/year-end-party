package com.changing.party.controller.management;

import com.changing.party.common.ServerConstant;
import com.changing.party.request.SendTicketEmailListRequest;
import com.changing.party.response.Response;
import com.changing.party.service.MailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/management")
@EnableWebMvc
@Log4j2
public class ManagementMailController {

    MailService mailService;

    public ManagementMailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping(value = "/mail")
    private Response sendEmails(@RequestBody @Valid SendTicketEmailListRequest sendTicketEmailRequestList) {
        boolean success = mailService.sendEmailByUserId(sendTicketEmailRequestList.getRecipients());
        return Response.builder()
                .errorCode(success ? ServerConstant.SERVER_SUCCESS_CODE : ServerConstant.SERVER_FAIL_CODE)
                .errorMessage(success ? ServerConstant.SERVER_SUCCESS_MESSAGE : ServerConstant.SERVER_FAIL_MESSAGE)
                .build();
    }
}
