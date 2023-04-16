package com.example.car3.service;

import com.example.car3.model.dto.MiniOrderDto;
import com.example.car3.model.entity.OrderEntity;
import com.example.car3.model.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderService {

    private final OrderRepository orderRepository;

    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;
    private final ServiceRepository serviceRepository;
    private final SpareRepository spareRepository;
    private final StatusRepository statusRepository;
    private final StsRepository stsRepository;

    @Transactional
    public void deleteOrder(Long id) {
        if (id == null || !orderRepository.existsById(id)) {
            throw new RuntimeException();
        }
        var order = orderRepository.findById(id).orElseThrow();
        order.setCar(null);
        order.setEmployee(null);
        order.setPerson(null);
        order.setService(null);
        order.setSpare(null);
        order.setStatus(null);
        order.setSts(null);
        order = orderRepository.save(order);
        orderRepository.delete(order);
        log.debug("Удаление успешно");
    }

    public OrderEntity saveOrder(MiniOrderDto request) {
        log.debug("Запрос на сохранение");
        log.debug(request.toString());

        OrderEntity order = OrderEntity.builder()
                .price(request.getPrice())
                .started(request.getStarted())
                .finished(request.getFinished())

                .car(request.getCar() == 0 ? null : carRepository.findById(request.getCar()).orElseThrow())
                .employee(request.getEmployee() == 0 ? null : employeeRepository.findById(request.getEmployee()).orElseThrow())
                .person(request.getPerson() == 0 ? null : personRepository.findById(request.getPerson()).orElseThrow())
                .service(request.getService() == 0 ? null : serviceRepository.findById(request.getService()).orElseThrow())
                .spare(request.getSpare() == 0 ? null : spareRepository.findById(request.getSpare()).orElseThrow())
                .status(request.getStatus() == 0 ? null : statusRepository.findById(request.getStatus()).orElseThrow())
                .sts(request.getSts() == 0 ? null : stsRepository.findById(request.getSts()).orElseThrow())
                .build();
        order.setId(request.getId() == null ? null : request.getId());
        order.setTitle(request.getTitle());
        return orderRepository.save(order);
    }
}