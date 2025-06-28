package com.harshal.beniwal.fairshare.controller;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.model.ApiResponse;
import com.harshal.beniwal.fairshare.model.user.UserRequestDTO;
import com.harshal.beniwal.fairshare.model.user.UserResponseDTO;
import com.harshal.beniwal.fairshare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createUser(
            @Valid @RequestBody UserRequestDTO userDto) {

        log.info("Received request to create user: {}", userDto);

        UserResponseDTO savedUser = userService.createUser(userDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(savedUser.toString(), "User created successfully"));
    }
}

