package com.otocar.otocar.controller;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

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

        Page<Advertisement> advertisements = advertisementService.find(page, brandName, modelName, lowRegistration, highRegistration, lowPrice, highPirce, orderBy);

        advertisements.forEach(advertisement -> {
            advertisement.setContent(null);
            advertisement.getCar().setColor(null);
            advertisement.getCar().setTypeCar(null);
            advertisement.getCar().setBrand(null);
            advertisement.getCar().setModel(null);
            advertisement.setSeller(null);
            advertisement.getCity().setAdvertisements(null);

        });
        return advertisements;
    }

    @GetMapping("seller/{id}")
    public Page<Advertisement> findAllAdvertisementBySeller(@PathVariable(value = "id") Long id, @RequestParam(defaultValue = "1")int page) {
        Page<Advertisement> advertisementPage = advertisementService.findAllAdvertisementBySeller(id,page);
        advertisementPage.forEach(advertisement -> advertisement.getCity().setAdvertisements(null));
        return advertisementPage;
    }

    @GetMapping("{id}")
    public Advertisement findAdvertismentById(@PathVariable(value = "id") Long id){
        return advertisementService.findAllById(id);
    }




    @PostMapping("")
    public Advertisement postSeller(@RequestBody Advertisement advertisement) {
        advertisement.setDateAdd(LocalDate.now());
        return advertisementService.addAdv(advertisement);
    }

    @PutMapping("/{id}")
    public Advertisement putSeller(@PathVariable(value = "id") Long id, @RequestBody Advertisement advertisement) {
        return advertisementService.change(id, advertisement);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        advertisementService.deleteById(id);
    }
}
