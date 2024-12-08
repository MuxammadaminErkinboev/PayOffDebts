package com.B31.Muxammadamin.repository;

import com.B31.Muxammadamin.entity.SuperAdmin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class SuperAdminRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public SuperAdmin findByUsername(String username) {
        return entityManager.find(SuperAdmin.class, username);
    }

    public SuperAdmin save(SuperAdmin superAdmin) {
        entityManager.persist(superAdmin);
        return superAdmin;
    }
}
