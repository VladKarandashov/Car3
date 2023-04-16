package com.example.car3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountOrdersByService {
    private String serviceTitle;
    private Long count;
}