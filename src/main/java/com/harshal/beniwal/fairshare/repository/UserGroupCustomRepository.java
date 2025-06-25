package com.harshal.beniwal.fairshare.repository;

import com.harshal.beniwal.fairshare.entity.User;
import com.harshal.beniwal.fairshare.entity.UserGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserGroupCustomRepository implements GroupCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsersById(UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<UserGroup> groupRoot = query.from(UserGroup.class);
        Join<UserGroup, User> usersJoin = groupRoot.join("users");

        query.select(usersJoin)
                .where(cb.equal(groupRoot.get("id"), id));

        return entityManager.createQuery(query).getResultList();
    }
}
