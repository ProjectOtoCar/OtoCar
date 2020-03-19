package com.otocar.otocar.controller;

import com.otocar.otocar.model.Brand;
import com.otocar.otocar.repository.BrandRepository;
import com.otocar.otocar.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Brand> find (){
        return brandService.findAll();
    }
}
