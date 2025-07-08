package com.harshal.beniwal.fairshare.model.user;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String fullName;
    private String email;
    private String password;
}
