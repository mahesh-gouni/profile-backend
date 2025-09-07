package com.neo.website.neoContactUsEmail.model;

import lombok.Data;

@Data
public class User {
    private String fullName;
    private String email;
    private String subject;
    private String message;
}
