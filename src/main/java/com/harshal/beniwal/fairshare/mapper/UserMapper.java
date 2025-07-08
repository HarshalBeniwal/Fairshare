package com.harshal.beniwal.fairshare.mapper;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.model.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "displayName", target = "userName")
    UserResponseDTO toResponseDTO(User user);

    List<UserResponseDTO> toResponseDTOList(List<User> users);
}
