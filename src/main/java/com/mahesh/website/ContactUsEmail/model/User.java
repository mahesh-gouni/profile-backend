package com.mahesh.website.ContactUsEmail.model;

import lombok.Data;

@Data
public class User {
    private String fullName;
    private String email;
    private String subject;
    private String message;
}
