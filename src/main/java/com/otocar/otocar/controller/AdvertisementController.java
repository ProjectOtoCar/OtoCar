package com.otocar.otocar.controller;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

//    @GetMapping("")
//    public Page<Advertisement> findAll(@RequestParam(defaultValue = "1") int page) {
//        return advertisementService.findAll(page);
//    }

    @GetMapping("/sort")
    public Page<Advertisement> findAllOrderByPrice(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(required = false) String brandName,
                                                   @RequestParam(required = false) String modelName,
                                                   @RequestParam(defaultValue = "0") BigDecimal lowPrice,
                                                   @RequestParam(defaultValue = "100000000") BigDecimal highPirce,
                                                   @RequestParam(defaultValue = "1940") int lowRegistration,
                                                   @RequestParam(defaultValue = "2020") int highRegistration,
                                                   @RequestParam(defaultValue = "desc") String orderBy
                                                   ) {
        return advertisementService.find(page,brandName,modelName,lowRegistration,highRegistration,lowPrice,highPirce,orderBy);

    }

}
