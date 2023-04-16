package com.example.car3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateResponse {
    private List<Item> list;
    private Item selected;
}