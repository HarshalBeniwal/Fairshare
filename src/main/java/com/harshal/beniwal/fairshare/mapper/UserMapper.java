package com.harshal.beniwal.fairshare.mapper;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.model.user.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponseDTO(User user);
}
