package com.harshal.beniwal.fairshare.model.group;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGroupRequestDTO {
    @NotBlank(message = "Group name is required")
    private String groupName;
    private Long createdByUserId;
}
