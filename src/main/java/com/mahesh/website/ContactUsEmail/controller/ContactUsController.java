package com.mahesh.website.ContactUsEmail.controller;

import com.mahesh.website.ContactUsEmail.model.User;
import com.mahesh.website.ContactUsEmail.service.ContactUsService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
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
