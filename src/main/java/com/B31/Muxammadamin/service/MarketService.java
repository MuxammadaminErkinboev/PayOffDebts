package com.B31.Muxammadamin.service;

import com.B31.Muxammadamin.entity.Market;
import com.B31.Muxammadamin.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketService implements MarketServiceInterface {

    private final MarketRepository marketRepository;

    @Override
    public Market authenticate(String name, String password) {
        Optional<Market> optionalMarket = marketRepository.findByName(name);

        if (optionalMarket.isPresent() && optionalMarket.get().getPassword().equals(password)) {
            return optionalMarket.get();
        }
        throw new RuntimeException("Invalid Market credentials!");
    }

    @Override
    public Market save(Market market) {
        if (market.getName() == null || market.getName().isEmpty()) {
            throw new IllegalArgumentException("Market name cannot be null or empty!");
        }
        if (market.getPassword() == null || market.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Market password cannot be null or empty!");
        }
        return marketRepository.save(market);
    }

    @Override
    public Market findById(int id) {
        return marketRepository.findById(id);
    }


    @Override
    public List<Market> findAll() {
        return marketRepository.findAll();
    }
}
