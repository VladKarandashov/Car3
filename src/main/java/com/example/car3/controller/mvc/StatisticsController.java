package com.example.car3.controller.mvc;

import com.example.car3.service.StatisticService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistic")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StatisticsController {

    private final StatisticService statisticService;

    @GetMapping
    public String show(Model model) throws JsonProcessingException {

        model.addAttribute("countOrders", statisticService.countOrder());
        model.addAttribute("countServices", statisticService.countService());
        model.addAttribute("countEmployees", statisticService.countEmployee());

        model.addAttribute("statistics", statisticService.jsonStatistics());

        return "/statistics";
    }
}