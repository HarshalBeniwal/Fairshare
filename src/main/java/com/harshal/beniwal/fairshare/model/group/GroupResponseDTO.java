package com.harshal.beniwal.fairshare.model.group;

import com.harshal.beniwal.fairshare.model.user.UserResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class GroupResponseDTO {
    private UUID groupId;
    private String groupName;
    private List<UserResponseDTO> users;
}
