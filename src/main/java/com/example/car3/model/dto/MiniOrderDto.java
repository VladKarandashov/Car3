package com.example.car3.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@Validated
@ToString
public class MiniOrderDto {

    private Long id;
    @NotBlank
    private String title;
    @Min(0)
    @NotNull
    private Double price;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate started;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate finished;

    private Long car;
    private Long employee;
    private Long person;
    private Long service;
    private Long spare;
    private Long status;
    private Long sts;

}