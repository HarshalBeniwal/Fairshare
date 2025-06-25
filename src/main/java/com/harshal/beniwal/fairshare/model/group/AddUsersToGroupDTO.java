package com.harshal.beniwal.fairshare.model.group;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
@Builder
public class AddUsersToGroupDTO {
    private List<Long> userIds;
}
