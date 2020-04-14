package com.otocar.otocar.controller;

import com.otocar.otocar.model.City;
import com.otocar.otocar.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    ResponseEntity<Iterable<City>> findAll(){
        return ResponseEntity.ok(cityService.findAll());
    }
}
