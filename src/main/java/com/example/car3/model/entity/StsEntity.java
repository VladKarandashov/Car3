package com.example.car3.model.entity;

import com.example.car3.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class StsEntity extends BaseEntity {

    public StsEntity(String title) {
        super(title);
    }
}
