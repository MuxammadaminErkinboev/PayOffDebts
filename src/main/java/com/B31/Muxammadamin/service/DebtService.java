package com.B31.Muxammadamin.service;

import com.B31.Muxammadamin.entity.Debt;
import com.B31.Muxammadamin.entity.Market;
import com.B31.Muxammadamin.entity.User;
import com.B31.Muxammadamin.repository.DebtRepository;
import com.B31.Muxammadamin.repository.MarketRepository;
import com.B31.Muxammadamin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtService implements DebtServiceInterface {

    private final DebtRepository debtRepository;
    private final MarketRepository marketRepository;
    private final UserRepository userRepository;

    @Override
    public void giveDebt(int userId, int marketId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Debt amount must be greater than zero.");
        }
        User user = userRepository.findById(userId);
        Market market = marketRepository.findById(marketId);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        if (market == null) {
            throw new RuntimeException("Market not found!");
        }

        Debt debt = new Debt();
        debt.setUser(user);
        debt.setMarket(market);
        debt.setAmount(amount);
        debtRepository.save(debt);
    }

    @Override
    public void payDebt(int debtId, double amount) {
        Debt debt = debtRepository.findById(debtId);
        if (debt == null) {
            throw new RuntimeException("Debt with ID " + debtId + " not found!");
        }

        if (amount <= 0 || amount > debt.getAmount()) {
            throw new IllegalArgumentException("Invalid payment amount.");
        }

        debt.setAmount(debt.getAmount() - amount);
        debt.setCreatedAt(LocalDateTime.now());  // Update timestamp if necessary

        if (debt.getAmount() == 0) {
            debtRepository.delete(debt);
        } else {
            debtRepository.save(debt);
        }
    }


    @Override
    public List<Debt> findByMarketId(int marketId) {
        return debtRepository.findByMarketId(marketId);
    }

    @Override
    public double calculateTotalDebt() {
        return debtRepository.calculateTotalDebt();
    }
}

