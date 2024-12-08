package com.B31.Muxammadamin;

import com.B31.Muxammadamin.entity.SuperAdmin;
import com.B31.Muxammadamin.repository.SuperAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class AdminDataInitializer {

    private final SuperAdminRepository superAdminRepository;

    @PostConstruct
    public void initializeAdminData() {
        SuperAdmin superAdmin = superAdminRepository.findByUsername("SuperSystemAdmin");
        if (superAdmin == null) {
            superAdmin = new SuperAdmin();
            superAdmin.setUsername("SuperSystemAdmin");
            superAdmin.setPassword("SuperAdmin");
            superAdminRepository.save(superAdmin);
        }
    }
}
