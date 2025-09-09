package com.mahesh.website.ContactUsEmail.service;



import com.mahesh.website.utils.VelocityUtility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class MailService {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.template}")
    private String templateDir;

    private final JavaMailSender mailSender;
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTemplateMail(final String to, final String subject,
                                 final Map<String, Object> params, String vmFileName)
            throws MessagingException, IOException {

        VelocityContext context = new VelocityContext();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }


        Resource resource = new ClassPathResource(this.templateDir);
        File file = resource.getFile();
        String vmFolder = file.getPath();

        String content = VelocityUtility.generateTemplate("templates/" + vmFileName, context);
        sendHtmlMail(to, subject, content);
        log.info("MailTemplate sent to {}", to);
    }

    public void sendHtmlMail(final String to, final String subject,
                             final String content) throws MessagingException {
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(this.username);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        this.mailSender.send(mimeMessage);
        log.info("Mail sent to {}", to);
    }
}

