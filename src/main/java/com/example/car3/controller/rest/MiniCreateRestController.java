package com.example.car3.controller.rest;

import com.example.car3.model.dto.CreateResponse;
import com.example.car3.service.MiniCreateService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miniCreate")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MiniCreateRestController {

    private final MiniCreateService miniCreateService;

    @PostMapping("/car")
    public CreateResponse car(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "car");
    }

    @PostMapping("/employee")
    public CreateResponse employee(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "employee");
    }

    @PostMapping("/person")
    public CreateResponse person(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "person");
    }

    @PostMapping("/service")
    public CreateResponse service(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "service");
    }

    @PostMapping("/spare")
    public CreateResponse spare(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "spare");
    }

    @PostMapping("/status")
    public CreateResponse status(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "status");
    }

    @PostMapping("/sts")
    public CreateResponse sts(@RequestBody CreateRequest request) {
        return miniCreateService.miniCreate(request.getTitle(), "sts");
    }

    @Data
    @ToString
    @NoArgsConstructor
    private static class CreateRequest {
        private String title;
    }
}