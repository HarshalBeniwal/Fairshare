package com.harshal.beniwal.fairshare.service;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.exception.DomainException;
import com.harshal.beniwal.fairshare.mapper.UserMapper;
import com.harshal.beniwal.fairshare.model.user.UserRequestDTO;
import com.harshal.beniwal.fairshare.model.user.UserResponseDTO;
import com.harshal.beniwal.fairshare.repository.GroupRepository;
import com.harshal.beniwal.fairshare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO userDto) {
        log.debug("Creating user: {}", userDto);

        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(existing -> {
                    throw new DomainException.UserException("Email already exists", HttpStatus.BAD_REQUEST);
                });

        User user = User.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .build();

        User saved = userRepository.save(user);

        return userMapper.toResponseDTO(saved);
    }
}
