package com.harshal.beniwal.fairshare.controller;

import com.harshal.beniwal.fairshare.model.ApiResponse;
import com.harshal.beniwal.fairshare.model.user.AuthRequest;
import com.harshal.beniwal.fairshare.model.user.RegisterRequest;
import com.harshal.beniwal.fairshare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest request) {
        String email = userService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(email, "User created successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody AuthRequest request) {
        String token = userService.loginUser(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(token, "Login successful"));
    }
}
