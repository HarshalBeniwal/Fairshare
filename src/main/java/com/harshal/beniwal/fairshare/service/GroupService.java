package com.harshal.beniwal.fairshare.service;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.entity.UserGroup;
import com.harshal.beniwal.fairshare.exception.DomainException;
import com.harshal.beniwal.fairshare.mapper.UserMapper;
import com.harshal.beniwal.fairshare.model.group.AddUsersToGroupDTO;
import com.harshal.beniwal.fairshare.model.group.GroupResponseDTO;
import com.harshal.beniwal.fairshare.model.group.UserGroupRequestDTO;
import com.harshal.beniwal.fairshare.repository.GroupRepository;
import com.harshal.beniwal.fairshare.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void createGroup(@Valid UserGroupRequestDTO userGroup) {
        Long userId = userGroup.getCreatedByUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DomainException.UserException("User with ID " + userId + " not found",
                        HttpStatus.NOT_FOUND));
       UserGroup userGroupEntity = UserGroup.builder()
                .groupName(userGroup.getGroupName())
                .createdBy(user)
               .users(new ArrayList<>(List.of(user)))
                .build();

        if (Boolean.TRUE.equals(groupRepository.existsByGroupName(userGroup.getGroupName()))) {
            throw new DomainException.GroupException("Group with name " + userGroup.getGroupName() + " already exists",
                    HttpStatus.BAD_REQUEST);
        }

        groupRepository.save(userGroupEntity);
    }


    @Transactional
    public void addUserToGroup(AddUsersToGroupDTO userGroupRequest, UUID groupId) {
        UserGroup userGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new DomainException.GroupException("Group with ID " + groupId + " not found",
                        HttpStatus.NOT_FOUND));

        List<User> usersToAdd = userRepository.findAllById(userGroupRequest.getUserIds());
        if (usersToAdd.isEmpty()) {
            throw new DomainException.GroupException("No valid users found for the provided IDs",
                    HttpStatus.BAD_REQUEST);
        }

        for (User user : usersToAdd) {
            if (userGroup.getUsers().contains(user)) {
                throw new DomainException.UserException("User with ID " + user.getId() + " is already part of the group",
                        HttpStatus.BAD_REQUEST);
            }
        }

        userGroup.getUsers().addAll(usersToAdd);
        groupRepository.save(userGroup);
    }

    public GroupResponseDTO getAllUsersInGroup(UUID groupId) {
        UserGroup userGroup= groupRepository.findById(groupId).orElseThrow(
                () -> new DomainException.GroupException("Group with ID " + groupId + " not found",
                        HttpStatus.NOT_FOUND));

        List<User> users = userGroup.getUsers();
        return GroupResponseDTO.builder()
                .groupId(userGroup.getId())
                .groupName(userGroup.getGroupName())
                .users(userMapper.toResponseDTOList(users))
                .build();
    }
}
