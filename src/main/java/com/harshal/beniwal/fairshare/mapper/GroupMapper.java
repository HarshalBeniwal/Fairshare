package com.harshal.beniwal.fairshare.mapper;


import com.harshal.beniwal.fairshare.entity.UserGroup;
import com.harshal.beniwal.fairshare.model.group.GroupResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
   GroupResponseDTO toResponseDTO(UserGroup group);
}
