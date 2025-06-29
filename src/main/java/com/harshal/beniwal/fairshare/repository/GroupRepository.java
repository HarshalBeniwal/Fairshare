package com.harshal.beniwal.fairshare.repository;

import com.harshal.beniwal.fairshare.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<UserGroup, UUID> {
    Boolean existsByGroupName(String groupName);

    Optional<UserGroup> findById(UUID groupId);
}
