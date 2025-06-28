package com.harshal.beniwal.fairshare.controller;


import com.harshal.beniwal.fairshare.model.ApiResponse;
import com.harshal.beniwal.fairshare.model.group.AddUsersToGroupDTO;
import com.harshal.beniwal.fairshare.model.group.GroupResponseDTO;
import com.harshal.beniwal.fairshare.model.group.UserGroupRequestDTO;
import com.harshal.beniwal.fairshare.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
@Log4j2
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createGroup(@Valid  @RequestBody UserGroupRequestDTO userGroupRequest) {
        log.info("Received request to create group: {}", userGroupRequest);

        groupService.createGroup(userGroupRequest);
        return ResponseEntity.ok(ApiResponse.success(null ,"Group created successfully"));
    }

    @PostMapping("/addUser/{groupId}")
    public ResponseEntity<ApiResponse<String>> addUserToGroup(@Valid @RequestBody AddUsersToGroupDTO userGroupRequest, @PathVariable UUID groupId) {
        log.info("Received request to add user to group: {}", userGroupRequest);

        groupService.addUserToGroup(userGroupRequest, groupId);
        return ResponseEntity.ok(ApiResponse.success(null, "User added to group successfully"));
    }

    @GetMapping("/{groupId}/users")
    public ResponseEntity<ApiResponse<GroupResponseDTO>> getAllUsersInGroup(@PathVariable UUID groupId) {
        log.info("Received request to get all users in group");

        GroupResponseDTO groupResponse = groupService.getAllUsersInGroup(groupId);
        return ResponseEntity.ok(ApiResponse.success(groupResponse, "Fetched all users in group successfully"));
    }
}
