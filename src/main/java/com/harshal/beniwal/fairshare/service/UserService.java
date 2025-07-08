package com.harshal.beniwal.fairshare.service;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.exception.DomainException;
import com.harshal.beniwal.fairshare.model.user.AuthRequest;
import com.harshal.beniwal.fairshare.model.user.RegisterRequest;
import com.harshal.beniwal.fairshare.repository.UserRepository;
import com.harshal.beniwal.fairshare.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String registerUser(RegisterRequest request) {
        log.debug("Registering user: {}", request.getEmail());
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DomainException.UserException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .displayName(request.getUsername())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return user.getEmail();
    }

    public String loginUser(AuthRequest request) {
        log.debug("Authenticating user: {}", request.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception ex) {
            log.error("Authentication failed for user: {}", request.getEmail(), ex);
            throw new DomainException.UserException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
        return jwtUtil.generateToken(request.getEmail());
    }
}
