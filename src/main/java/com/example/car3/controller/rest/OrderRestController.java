package com.example.car3.controller.rest;

import com.example.car3.model.dto.MiniOrderDto;
import com.example.car3.model.entity.OrderEntity;
import com.example.car3.service.OrderService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/delete")
    public void delete(@RequestBody DeleteRequest request) {
        Long id = request.getId();
        orderService.deleteOrder(id);
    }

    @PostMapping("/save")
    public OrderEntity save(@Valid @RequestBody MiniOrderDto request) {
        return orderService.saveOrder(request);
    }

    @Data
    private static class DeleteRequest {
        Long id;
    }
}