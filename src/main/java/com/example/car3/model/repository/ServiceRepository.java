package com.example.car3.model.repository;

import com.example.car3.model.BaseRepository;
import com.example.car3.model.entity.CarEntity;
import com.example.car3.model.entity.ServiceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends BaseRepository<ServiceEntity> {

}
