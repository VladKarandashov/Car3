package com.example.car3.service;

import com.example.car3.model.entity.*;
import com.example.car3.model.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EditorService {

    private final OrderRepository orderRepository;

    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;
    private final ServiceRepository serviceRepository;
    private final SpareRepository spareRepository;
    private final StatusRepository statusRepository;
    private final StsRepository stsRepository;

    public Long getFirstProduct() {
        return orderRepository.findFirstRowId();
    }

    public void fillModel(Long id, Model model) {
        Long firstFilmId = orderRepository.findFirstRowId();
        Long lastFilmId = orderRepository.findLastRowId();
        model.addAttribute("firstId", firstFilmId);
        model.addAttribute("lastId", lastFilmId);

        Long prevId = orderRepository.getPreviousId(id);
        Long nextId = orderRepository.getNextId(id);
        prevId = prevId == null ? lastFilmId : prevId;
        nextId = nextId == null ? firstFilmId : nextId;
        model.addAttribute("prevId", prevId);
        model.addAttribute("nextId", nextId);

        OrderEntity order;
        if (id == 0) {
            order = OrderEntity.builder()
                    .car(new CarEntity())
                    .employee(new EmployeeEntity())
                    .person(new PersonEntity())
                    .service(new ServiceEntity())
                    .spare(new SpareEntity())
                    .status(new StatusEntity())
                    .sts(new StsEntity())
                    .build();
        } else {
            order = orderRepository.findById(id).orElseThrow();
            if (order.getCar() == null) order.setCar(new CarEntity());
            if (order.getEmployee() == null) order.setEmployee(new EmployeeEntity());
            if (order.getPerson() == null) order.setPerson(new PersonEntity());
            if (order.getService() == null) order.setService(new ServiceEntity());
            if (order.getSpare() == null) order.setSpare(new SpareEntity());
            if (order.getStatus() == null) order.setStatus(new StatusEntity());
            if (order.getSts() == null) order.setSts(new StsEntity());
        }

        model.addAttribute("order", order);

        model.addAttribute("cars", carRepository.findAll().stream()
                .sorted(Comparator.comparing(CarEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("employees", employeeRepository.findAll().stream()
                .sorted(Comparator.comparing(EmployeeEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("persons", personRepository.findAll().stream()
                .sorted(Comparator.comparing(PersonEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("services", serviceRepository.findAll().stream()
                .sorted(Comparator.comparing(ServiceEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("spares", spareRepository.findAll().stream()
                .sorted(Comparator.comparing(SpareEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("statuses", statusRepository.findAll().stream()
                .sorted(Comparator.comparing(StatusEntity::getTitle))
                .collect(Collectors.toList()));
        model.addAttribute("stses", stsRepository.findAll().stream()
                .sorted(Comparator.comparing(StsEntity::getTitle))
                .collect(Collectors.toList()));
    }
}