package com.example.car3.controller.mvc;

import com.example.car3.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public String show(@RequestParam(required = false, defaultValue = "0") Long status,
                       @RequestParam(required = false, defaultValue = "0") Long service,
                       @RequestParam(required = false, defaultValue = "0") Long spare,
                       Model model) {
        searchService.fillModel(status, service, spare, model);
        return "/search";
    }
}