package com.otocar.otocar.controller;

import com.otocar.otocar.model.CarModel;
import com.otocar.otocar.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carModel")
public class CarModelController {

    private CarModelService carModelSevice;

    @Autowired
    public CarModelController(CarModelService carModelSevice) {
        this.carModelSevice = carModelSevice;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CarModel> find(@RequestParam()String brand){
        return carModelSevice.find(brand);
    }
}
