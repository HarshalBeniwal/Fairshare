package com.harshal.beniwal.fairshare.repository;

import com.harshal.beniwal.fairshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
