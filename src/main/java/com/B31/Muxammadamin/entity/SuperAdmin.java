package com.B31.Muxammadamin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SuperAdmin {
    @Id
    private String username;
    private String password;

}

