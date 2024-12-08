package com.B31.Muxammadamin.service;

import com.B31.Muxammadamin.entity.Debt;

import java.util.List;

public interface DebtServiceInterface {
    void giveDebt(int userId, int marketId, double amount);
    void payDebt(int debtId, double amount);
    List<Debt> findByMarketId(int marketId);
    double calculateTotalDebt();
}
