package com.B31.Muxammadamin.service;

import com.B31.Muxammadamin.entity.SuperAdmin;
import com.B31.Muxammadamin.repository.SuperAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperAdminService implements SuperAdminServiceInterface {

    private final SuperAdminRepository superAdminRepository;

    @Override
    public SuperAdmin authenticate(String username, String password) {
        SuperAdmin superAdmin = superAdminRepository.findByUsername(username);
        if (superAdmin != null && superAdmin.getPassword().equals(password)) {
            return superAdmin;
        } throw new RuntimeException("Invalid SuperAdmin credentials!");
    }
}
