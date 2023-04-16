package com.example.car3.service;

import com.example.car3.model.dto.CreateResponse;
import com.example.car3.model.dto.Item;
import com.example.car3.model.entity.*;
import com.example.car3.model.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MiniCreateService {

    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;
    private final ServiceRepository serviceRepository;
    private final SpareRepository spareRepository;
    private final StatusRepository statusRepository;
    private final StsRepository stsRepository;

    public CreateResponse miniCreate(@NonNull String title, String obj) {
        switch (obj) {
            case "car" -> {
                return createCar(title);
            }
            case "employee" -> {
                return createEmployee(title);
            }
            case "person" -> {
                return createPerson(title);
            }
            case "service" -> {
                return createService(title);
            }
            case "spare" -> {
                return createSpare(title);
            }
            case "status" -> {
                return createStatus(title);
            }
            case "sts" -> {
                return createSts(title);
            }
        }
        return null;
    }

    private CreateResponse createCar(String title) {
        assert carRepository.existsByTitle(title);
        var newObj = carRepository.save(new CarEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = carRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createEmployee(String title) {
        assert employeeRepository.existsByTitle(title);
        var newObj = employeeRepository.save(new EmployeeEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = employeeRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createPerson(String title) {
        assert personRepository.existsByTitle(title);
        var newObj = personRepository.save(new PersonEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = personRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createService(String title) {
        assert serviceRepository.existsByTitle(title);
        var newObj = serviceRepository.save(new ServiceEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = serviceRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createSpare(String title) {
        assert spareRepository.existsByTitle(title);
        var newObj = spareRepository.save(new SpareEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = spareRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createStatus(String title) {
        assert statusRepository.existsByTitle(title);
        var newObj = statusRepository.save(new StatusEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = statusRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }

    private CreateResponse createSts(String title) {
        assert stsRepository.existsByTitle(title);
        var newObj = stsRepository.save(new StsEntity(title));
        var item = new Item(newObj.getId(), newObj.getTitle());
        List<Item> items = stsRepository.findAll().stream().map(el -> new Item(el.getId(), el.getTitle())).sorted(Comparator.comparing(Item::getTitle)).toList();
        return new CreateResponse(items, item);
    }
}