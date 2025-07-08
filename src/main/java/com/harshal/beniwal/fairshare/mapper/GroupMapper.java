package com.harshal.beniwal.fairshare.mapper;


import com.harshal.beniwal.fairshare.entity.UserGroup;
import com.harshal.beniwal.fairshare.model.group.GroupResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {
   @Mapping(source = "group.id", target = "groupId")
   GroupResponseDTO toResponseDTO(UserGroup group);
}
