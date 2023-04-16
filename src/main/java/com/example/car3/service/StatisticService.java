package com.example.car3.service;

import com.example.car3.model.dto.CountOrdersByService;
import com.example.car3.model.entity.ServiceEntity;
import com.example.car3.model.repository.EmployeeRepository;
import com.example.car3.model.repository.OrderRepository;
import com.example.car3.model.repository.ServiceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatisticService {
    private final OrderRepository orderRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;

    public Long countOrder() {
        return orderRepository.count();
    }

    public Long countService() {
        return serviceRepository.count();
    }

    public Long countEmployee() {
        return employeeRepository.count();
    }

    public List<CountOrdersByService> statistics() {
        return serviceRepository.findAll().parallelStream()
                .map(service ->
                        new CountOrdersByService(
                                service.getTitle(),
                                getCountOrdersByService(service)
                        )
                ).collect(Collectors.toList());
    }

    public String jsonStatistics() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(statistics());
    }


    public Long getCountOrdersByService(ServiceEntity service) {
        return (long) orderRepository.findAllByService(service).size();
    }
}