package com.harshal.beniwal.fairshare.controller;


import com.harshal.beniwal.fairshare.model.ApiResponse;
import com.harshal.beniwal.fairshare.model.group.UserGroupRequestDTO;
import com.harshal.beniwal.fairshare.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//
//    @GetMapping
//    public ResponseEntity<ApiResponse<String>> getGroups() {
//        log.info("Received request to get all groups");
//
//        // Assuming a method exists in GroupService to fetch all groups
//        String groups = groupService.getAllGroups();
//        return ResponseEntity.ok(ApiResponse.success(groups, "Groups retrieved successfully"));
//    }
}
