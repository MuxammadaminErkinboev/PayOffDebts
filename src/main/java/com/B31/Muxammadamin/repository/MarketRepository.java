package com.B31.Muxammadamin.repository;

import com.B31.Muxammadamin.entity.Market;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class MarketRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Market save(Market market) {
        entityManager.persist(market);
        return market;
    }

    public Market findById(int id) {
        return entityManager.find(Market.class, id);
    }


    public Optional<Market> findByName(String name) {
        return Optional.ofNullable(entityManager.createQuery("SELECT m FROM Market m WHERE m.name = :name", Market.class)
               .setParameter("name", name)
               .getResultList()
               .stream()
               .findFirst()
               .orElse(null));
    }

    public List<Market> findAll() {
        return entityManager.createQuery("SELECT m FROM Market m", Market.class)
               .getResultList();
    }
}
