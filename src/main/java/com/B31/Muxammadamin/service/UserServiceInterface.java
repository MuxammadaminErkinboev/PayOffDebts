package com.B31.Muxammadamin.service;

import com.B31.Muxammadamin.entity.Debt;
import com.B31.Muxammadamin.entity.User;

import java.util.List;

public interface UserServiceInterface {
    User authenticate(String username, String password);
    User save(User user);
    List<Debt> findUserDebts(int userId);
}
