package com.harshal.beniwal.fairshare.model.group;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserGroupRequest {
    private String groupName;
    private String userEmailId;
}
