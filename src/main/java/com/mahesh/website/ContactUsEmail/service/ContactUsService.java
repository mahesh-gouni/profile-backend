package com.mahesh.website.ContactUsEmail.service;

import com.mahesh.website.ContactUsEmail.model.User;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContactUsService {

    private static final Logger log = LoggerFactory.getLogger(ContactUsService.class);

    private final MailService mailService;

    public ContactUsService(MailService mailService) {
        this.mailService = mailService;
    }

    public void sendVerificationEmail(User user) {
        try {
            String email = user.getEmail();
            String name = user.getFullName();
            String subject = "THANK FOR YOUR CONTACTING US";

            Map<String, Object> params = new HashMap<>();
            params.put("name", name);

            String vmFileName = "Contactus.vm";


            sendVerificationEmailtoadmin(user);


            mailService.sendTemplateMail(email, subject, params, vmFileName);

            log.info("Verification email successfully sent to user: {}", email);

        } catch (MessagingException | IOException e) {
            log.error("Failed to send verification email to user: {}", user.getEmail(), e);
            // Optional: throw a custom exception or handle gracefully
        }
    }

    private void sendVerificationEmailtoadmin(User user) {
        try {
            String email = "venkatkollu23@gmail.com";
            String adminName = "neo admin";
            String subject = "Feedback from User";

            Map<String, Object> params = new HashMap<>();
            params.put("name", adminName);
            params.put("username", user.getFullName());
            params.put("useremail", user.getEmail());
            params.put("usermobile", user.getSubject()); // seems like phone number?
            params.put("usermessage", user.getMessage());

            String vmFileName = "admin.vm";

            mailService.sendTemplateMail(email, subject, params, vmFileName);

            log.info("Admin notification email successfully sent for user: {}", user.getEmail());

        } catch (MessagingException | IOException e) {
            log.error("Failed to send verification email to admin for user: {}", user.getEmail(), e);
            // Optional: throw a custom exception or handle gracefully
        }
    }
}
