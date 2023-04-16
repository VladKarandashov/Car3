package com.example.car3.service;

import com.example.car3.model.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ExportService {

    private final OrderRepository repository;

    public void fillModel(Model model) {
        model.addAttribute("orders", repository.findAllBy());
    }

    public ResponseEntity<Resource> jsonFile() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String films = objectMapper.writeValueAsString(repository.findAllBy().stream().toList());
        ByteArrayResource resource = new ByteArrayResource(films.getBytes());

        var currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        String currentDateTimeString = currentDateTime.format(formatter);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "orders " + currentDateTimeString + ".json")
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);
    }

}