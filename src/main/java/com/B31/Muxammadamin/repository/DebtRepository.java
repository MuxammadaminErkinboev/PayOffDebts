package com.B31.Muxammadamin.repository;

import com.B31.Muxammadamin.entity.Debt;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DebtRepository {
    private final SessionFactory sessionFactory;

    @Transactional
    public void save(Debt debt) {
        sessionFactory.getCurrentSession().merge(debt);
    }

    @Transactional
    public void delete(Debt debt) {
        sessionFactory.getCurrentSession().remove(debt);
    }

    @Transactional
    public Debt findById(int debtId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Debt.class, debtId);
    }

    @Transactional
    public List<Debt> findByUserId(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Debt WHERE user_id = :userId", Debt.class)
                .setParameter("userId", userId)
                .list();
    }

    @Transactional
    public List<Debt> findByMarketId(int marketId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Debt WHERE market_id = :marketId", Debt.class)
                .setParameter("marketId", marketId)
                .list();
    }

    @Transactional
    public double calculateTotalDebt() {
        Session session = sessionFactory.getCurrentSession();
        List<Debt> debts = session.createQuery("FROM Debt", Debt.class).list();
        double totalDebt = 0.0;
        for (Debt debt : debts) {
            totalDebt += debt.getAmount();
        }
        return totalDebt;
    }
}

