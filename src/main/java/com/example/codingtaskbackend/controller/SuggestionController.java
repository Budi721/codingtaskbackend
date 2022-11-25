package com.example.codingtaskbackend.controller;

import com.example.codingtaskbackend.model.Suggestion;
import com.example.codingtaskbackend.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService service;

    @Autowired
    public SuggestionController(SuggestionService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, List<Suggestion>> getSuggestions(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude
    ) {
        List<Suggestion> suggestions = service.getSuggestions(query, latitude, longitude);
        Map<String, List<Suggestion>> map = new HashMap<String, List<Suggestion>>();
        map.put("suggestions", suggestions);
        return map;
    }
}
