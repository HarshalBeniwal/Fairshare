package com.harshal.beniwal.fairshare.mapper;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.model.user.UserResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponseDTO(User user);
    List<UserResponseDTO> toResponseDTOList(List<User> users);
}
