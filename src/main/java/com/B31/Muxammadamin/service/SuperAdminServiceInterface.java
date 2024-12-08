package com.B31.Muxammadamin.service;

import com.B31.Muxammadamin.entity.SuperAdmin;

public interface SuperAdminServiceInterface {
    SuperAdmin authenticate(String username, String password);
}
