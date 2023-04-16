package com.example.car3.model.repository;

import com.example.car3.model.BaseRepository;
import com.example.car3.model.entity.OrderEntity;
import com.example.car3.model.entity.ServiceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<OrderEntity> {

    @Query("SELECT id FROM OrderEntity ORDER BY id ASC LIMIT 1")
    Long findFirstRowId();

    @Query("SELECT id FROM OrderEntity ORDER BY id DESC LIMIT 1")
    Long findLastRowId();

    @Query("SELECT MAX(id) FROM OrderEntity WHERE id < :myId")
    Long getPreviousId(@Param("myId") Long myId);

    @Query("SELECT MIN(id) FROM OrderEntity WHERE id > :myId")
    Long getNextId(@Param("myId") Long myId);

    List<OrderEntity> findAllByService(ServiceEntity service);
}
