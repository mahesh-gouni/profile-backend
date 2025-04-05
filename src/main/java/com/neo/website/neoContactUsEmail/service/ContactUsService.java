package com.neo.website.neoContactUsEmail.service;



import com.neo.website.neoContactUsEmail.model.User;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContactUsService {


    private final MailService mailService;


    public ContactUsService(MailService mailService) {
        this.mailService = mailService;
    }

    public void sendVerificationEmail(User user) throws MessagingException, IOException {

        String email = user.getEmail();
        String name = user.getName();
        String subject = "THANK FOR YOUR CONTACTING US";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        String vmFileName = "Contactus.vm";

sendVerificationEmailtoadmin(user);
        mailService.sendTemplateMail(email, subject, params, vmFileName);

    }
    public void sendVerificationEmailtoadmin(User user) throws MessagingException, IOException {

        String email = "venkatkollu23@gmail.com  ";
        String adminname = "neo admin";
        String subject = "Feedback from User";

        Map<String, Object> params = new HashMap<>();
        params.put("name", adminname);
        params.put("username", user.getName());
        params.put("useremail", user.getEmail());
        params.put("usermobile", user.getMobile());
        params.put("usermessage", user.getMessage());

        String vmFileName = "admin.vm";

        mailService.sendTemplateMail(email, subject, params, vmFileName);
    }



}

