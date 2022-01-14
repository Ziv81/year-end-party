package com.changing.party.service;

import com.changing.party.common.CGByteArrayResource;
import com.changing.party.dto.UserModelDTO;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
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

@Log4j2
@Service
public class MailService {

    private JavaMailSender mailSender;
    private Configuration mailConfiguration;

    private UserService userService;
    private ImageService imageService;

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.UTF_8);

    public MailService(JavaMailSender mailSender, Configuration mailConfiguration, UserService userService, ImageService imageService) {
        this.mailSender = mailSender;
        this.mailConfiguration = mailConfiguration;
        this.userService = userService;
        this.imageService = imageService;
    }

    public boolean sendEmailByUserId(List<Integer> userIdList) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            userIdList.forEach(x -> {
                try {
                    String plainTextPassword = RandomStringUtils.randomAlphanumeric(5);
                    String sha256Password = byteToHex(digest.digest(
                            plainTextPassword.getBytes(StandardCharsets.UTF_8))).toLowerCase(Locale.ROOT);

                    UserModelDTO userModelDTO = userService.getUserById(x);
                    sendEmail(userModelDTO, getUserNamePasswordImage(userModelDTO.getLoginName(), plainTextPassword));
                    userService.updateUserPassword(userModelDTO, sha256Password);
                } catch (Exception ex) {
                    log.error("Send email occur exception", ex);
                }
            });
        } catch (Exception ex) {
            log.error("Send mail occur exception", ex);
            return false;
        }
        return true;
    }

    private byte[] getUserNamePasswordImage(String loginUserName, String password) throws IOException {
        ByteArrayOutputStream os = imageService.imageAddText(loginUserName, password);
        return os.toByteArray();
    }

    private void sendEmail(UserModelDTO user, byte[] image) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("ziv@changingtec.com");
        helper.setTo(user.getEmail());
        helper.setSubject("尾牙測試");

        String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(mailConfiguration.getTemplate("SendPasswordTemplate.html"), new HashMap<>());
        helper.setText(templateString, true);

        CGByteArrayResource byteArrayResource = new CGByteArrayResource(image);
        helper.addInline("ticket", byteArrayResource);
        mailSender.send(mimeMessage);
    }

    private String byteToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);

    }
}
