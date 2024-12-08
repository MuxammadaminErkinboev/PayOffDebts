package com.B31.Muxammadamin.service;

import com.B31.Muxammadamin.entity.Market;

import java.util.List;

public interface MarketServiceInterface{
    Market authenticate(String name, String password);
    Market save(Market market);
    Market findById(int id);
    List<Market> findAll();
}
