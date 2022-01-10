package com.changing.party.service;

import freemarker.template.Configuration;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class MailService {

    private JavaMailSender mailSender;
    private Configuration mailConfiguration;

    public MailService(JavaMailSender mailSender, Configuration mailConfiguration) {
        this.mailSender = mailSender;
        this.mailConfiguration = mailConfiguration;
    }

    public boolean sendEmailByUserId(List<Integer> userIdList) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("ziv@changingtec.com");
            helper.setTo("ziv@changingtec.com");
            helper.setSubject("尾牙測試");

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("userName", "Ziv");
            String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(mailConfiguration.getTemplate("SendPasswordTemplate.html"), model);
            helper.setText(templateString, true);
            Resource resource = new ClassPathResource("TestMailImage.jpg");
            File fileTest = resource.getFile();
            FileSystemResource file = new FileSystemResource(resource.getFile());
            helper.addInline("mailImage", file);

            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            log.error("Send mail occur exception", ex);
            return false;
        }
        return true;
    }
}
