package com.example.car3.service;

import com.example.car3.model.entity.OrderEntity;
import com.example.car3.model.entity.ServiceEntity;
import com.example.car3.model.entity.SpareEntity;
import com.example.car3.model.entity.StatusEntity;
import com.example.car3.model.repository.OrderRepository;
import com.example.car3.model.repository.ServiceRepository;
import com.example.car3.model.repository.SpareRepository;
import com.example.car3.model.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SearchService {
    private final StatusRepository statusRepository;
    private final ServiceRepository serviceRepository;
    private final SpareRepository spareRepository;

    private final OrderRepository orderRepository;

    public void fillModel(Long idStatus, Long idService, Long idSpare, Model model) {
        model.addAttribute("idStatus", idStatus);
        model.addAttribute("idService", idService);
        model.addAttribute("idSpare", idSpare);

        var status = idStatus == 0 ?
                new StatusEntity() :
                statusRepository.findById(idStatus).orElseThrow(() -> new RuntimeException("Не твори фигню!"));
        var service = idService == 0 ?
                new ServiceEntity() :
                serviceRepository.findById(idService).orElseThrow(() -> new RuntimeException("Не твори фигню!"));
        var spare = idSpare == 0 ?
                new SpareEntity() :
                spareRepository.findById(idSpare).orElseThrow(() -> new RuntimeException("Не твори фигню!"));

        var orders = orderRepository.findAllBy().parallelStream()
                .filter(el -> idStatus == 0 || status.equals(el.getStatus()))
                .filter(el -> idService == 0 || service.equals(el.getService()))
                .filter(el -> idSpare == 0 || spare.equals(el.getSpare()))
                .toList();
        model.addAttribute("orders", orders);

        var statuses = orders.stream()
                .map(OrderEntity::getStatus)
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.comparing(StatusEntity::getTitle))
                .toList();
        var services = orders.stream()
                .map(OrderEntity::getService)
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.comparing(ServiceEntity::getTitle))
                .toList();
        var spares = orders.stream()
                .map(OrderEntity::getSpare)
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.comparing(SpareEntity::getTitle))
                .toList();
        model.addAttribute("statuses", statuses);
        model.addAttribute("services", services);
        model.addAttribute("spares", spares);
    }
}