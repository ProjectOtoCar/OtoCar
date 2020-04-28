package com.otocar.otocar.controller;

import com.otocar.otocar.dto.AdvertisementDto;
import com.otocar.otocar.model.*;
import com.otocar.otocar.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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
        Set<Image> imageTreeSet = new HashSet<>();
        advertisementPage.forEach(advertisement -> {
            advertisement.getCity().setAdvertisements(null);
            advertisement.getCar().setBrand(null);
            advertisement.getCar().setModel(null);
            advertisement.getCar().setColor(null);
            advertisement.getCar().setTypeCar(null);
            advertisement.getSeller().setFirstName(null);
            advertisement.getSeller().setLastName(null);
            advertisement.getSeller().setType(null);
            advertisement.getSeller().setPhoneNumber(null);
            advertisement.getSeller().setCreateAccount(null);
            if(advertisement.getImages().size() > 1) {
                advertisement.getImages().forEach(image -> {
                    if(image.isMainImage() && imageTreeSet.size() < 1) {
                        imageTreeSet.add(image);
                    }
                });
                advertisement.setImages(imageTreeSet);
            }
        });
        return advertisementPage;
    }

    @GetMapping("{id}")
    public Advertisement findAdvertismentById(@PathVariable(value = "id") Long id){
        return advertisementService.findAllById(id);
    }




    @PostMapping("")
    public Advertisement addAdvertisemnt(@RequestBody AdvertisementDto advertisementDto) {
        Advertisement advertisement = new Advertisement(advertisementDto);
        advertisement.getImages().forEach(image -> image.setAdvertisement(advertisement));
        return advertisementService.addAdv(advertisement);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(value =  HttpStatus.OK)
    public void patch(@PathVariable(value = "id") Long id, @RequestBody Map<String, Object> fields ) {
        advertisementService.patch(id, fields);
    }

    @PutMapping("/{id}")
    public Advertisement putAdvertisement(@PathVariable(value = "id") Long id, @RequestBody AdvertisementDto advertisementDto) {
        Advertisement advertisement = new Advertisement(advertisementDto);
        advertisement.getImages().forEach(image -> image.setAdvertisement(advertisement));
        return advertisementService.change(id, advertisement);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        advertisementService.deleteById(id);
    }
}
