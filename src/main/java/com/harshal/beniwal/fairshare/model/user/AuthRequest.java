package com.harshal.beniwal.fairshare.model.user;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}