package com.harshal.beniwal.fairshare.repository;

import com.harshal.beniwal.fairshare.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<UserGroup, UUID> , GroupCustomRepository {
    Boolean existsByGroupName(String groupName);
}
