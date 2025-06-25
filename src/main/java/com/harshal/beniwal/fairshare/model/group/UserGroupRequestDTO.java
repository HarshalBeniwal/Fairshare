package com.harshal.beniwal.fairshare.model.group;


import com.harshal.beniwal.fairshare.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class UserGroupRequestDTO {
    @NotBlank(message = "Group name is required")
    private String groupName;
    private Long createdByUserId;
}
