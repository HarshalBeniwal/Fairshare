package com.harshal.beniwal.fairshare.service;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.entity.UserGroup;
import com.harshal.beniwal.fairshare.exception.DomainException;
import com.harshal.beniwal.fairshare.model.group.UserGroupRequestDTO;
import com.harshal.beniwal.fairshare.repository.GroupRepository;
import com.harshal.beniwal.fairshare.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public void createGroup(@Valid UserGroupRequestDTO userGroup) {
        Long userId = userGroup.getCreatedByUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DomainException.UserException("User with ID " + userId + " not found",
                        HttpStatus.NOT_FOUND));
       UserGroup userGroupEntity = UserGroup.builder()
                .groupName(userGroup.getGroupName())
                .createdBy(user)
                .build();

        if (groupRepository.existsByGroupName(userGroup.getGroupName())) {
            throw new DomainException.GroupException("Group with name " + userGroup.getGroupName() + " already exists",
                    HttpStatus.BAD_REQUEST);
        }

        groupRepository.save(userGroupEntity);
    }

}
