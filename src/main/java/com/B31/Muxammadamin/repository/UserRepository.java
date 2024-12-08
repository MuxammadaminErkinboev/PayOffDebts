package com.B31.Muxammadamin.repository;

import com.B31.Muxammadamin.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public User findByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public User findById(int userId) {
        return entityManager.find(User.class, userId);
    }

    public boolean existsByUsername(String username) {
        try {
            return entityManager.createQuery("select 1 from User u where u.username = :username", Integer.class)
                    .setParameter("username", username)
                    .getSingleResult() > 0;
        } catch (NoResultException e) {
            return false;
        }
    }

}
