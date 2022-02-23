package com.changing.party.service;

import com.changing.party.common.CGByteArrayResource;
import com.changing.party.common.Utils;
import com.changing.party.common.exception.IdWithLoginNameNotPairException;
import com.changing.party.dto.UserModelDTO;
import com.changing.party.request.SendTicketEmailRequest;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@Log4j2
@Service
public class MailService {

    private JavaMailSender mailSender;
    private Configuration mailConfiguration;

    private UserService userService;
    private ImageService imageService;


    public MailService(JavaMailSender mailSender, Configuration mailConfiguration, UserService userService, ImageService imageService) {
        this.mailSender = mailSender;
        this.mailConfiguration = mailConfiguration;
        this.userService = userService;
        this.imageService = imageService;
    }

    public boolean sendEmailByUserId(List<SendTicketEmailRequest> userIdList) {
        try {
            AtomicBoolean success = new AtomicBoolean(true);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            userIdList.forEach(x -> {
                try {
                    String plainTextPassword = x.getPassword();
                    String sha256Password = Utils.byteToHex(digest.digest(
                            plainTextPassword.getBytes(StandardCharsets.UTF_8))).toLowerCase(Locale.ROOT);

                    UserModelDTO userModelDTO = userService.getUserById(x.getUserId());
                    if (!userModelDTO.getLoginName().equals(x.getLoginName()))
                        throw new IdWithLoginNameNotPairException(String.format("Expect : %s-%s, Actual : %s-%s",
                                userModelDTO.getUserId(),
                                userModelDTO.getLoginName(),
                                x.getUserId(),
                                x.getLoginName()));
                    sendEmail(userModelDTO,
                            getUserNamePasswordImage(userModelDTO.getLoginName(), plainTextPassword),
                            plainTextPassword);
                    userService.updateUserPassword(userModelDTO, sha256Password);
                    log.info("Send email to {} success.", userModelDTO.getLoginName());
                } catch (Exception ex) {
                    log.info("Send email to {} fail.", x.getLoginName());
                    log.error("Send email occur exception", ex);
                    success.set(false);
                }
            });
            return success.get();
        } catch (Exception ex) {
            log.error("Send mail occur exception", ex);
            return false;
        }
    }

    private byte[] getUserNamePasswordImage(String loginUserName, String password) throws IOException {
        ByteArrayOutputStream os = imageService.imageAddText(loginUserName, password);
        return os.toByteArray();
    }

    private void sendEmail(UserModelDTO user, byte[] image, String password) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("ziv@changingtec.com");
        helper.setTo(user.getEmail());
        helper.setSubject("全景娛樂城-活動邀請函");

        HashMap<String, String> values = new HashMap<>();
        values.put("userName", user.getLoginName());
        values.put("password", password);
        String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(mailConfiguration.getTemplate("SendPasswordTemplate.html"), values);
        helper.setText(templateString, true);

        CGByteArrayResource byteArrayResource = new CGByteArrayResource(image);
        helper.addInline("ticket", byteArrayResource);
        helper.addAttachment("操作說明.png", new ClassPathResource("UserGuide.png").getFile());
        mailSender.send(mimeMessage);
    }


}
