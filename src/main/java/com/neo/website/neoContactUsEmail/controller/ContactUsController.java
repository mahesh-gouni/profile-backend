package com.neo.website.neoContactUsEmail.controller;

import com.neo.website.neoContactUsEmail.model.User;
import com.neo.website.neoContactUsEmail.service.ContactUsService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/neo")
public class ContactUsController {
    private final ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService=contactUsService;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendingReviewResponse(@RequestBody User user) throws MessagingException, IOException {
        contactUsService.sendVerificationEmail(user);
        return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
    }
}
