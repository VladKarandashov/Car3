package com.example.car3.model.repository;

import com.example.car3.model.BaseRepository;
import com.example.car3.model.entity.CarEntity;
import com.example.car3.model.entity.PersonEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<PersonEntity> {

}
