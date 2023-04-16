package com.example.car3.model.entity;

import com.example.car3.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity {
    private Double price;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate started;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate finished;


    @ManyToOne(cascade = {CascadeType.ALL})
    private CarEntity car;
    @ManyToOne(cascade = {CascadeType.ALL})
    private EmployeeEntity employee;
    @ManyToOne(cascade = {CascadeType.ALL})
    private PersonEntity person;
    @ManyToOne(cascade = {CascadeType.ALL})
    private ServiceEntity service;
    @ManyToOne(cascade = {CascadeType.ALL})
    private SpareEntity spare;
    @ManyToOne(cascade = {CascadeType.ALL})
    private StatusEntity status;
    @ManyToOne(cascade = {CascadeType.ALL})
    private StsEntity sts;
}
