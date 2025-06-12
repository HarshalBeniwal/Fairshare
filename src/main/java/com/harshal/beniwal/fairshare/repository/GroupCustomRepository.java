package com.harshal.beniwal.fairshare.repository;

import com.harshal.beniwal.fairshare.entity.User;

import java.util.List;
import java.util.UUID;

public interface GroupCustomRepository {
    List<User> getUsersById(UUID id);
}
