package com.otocar.otocar.controller;

import com.otocar.otocar.model.CarModel;
import com.otocar.otocar.repository.CarModelRepository;
import com.otocar.otocar.service.CarModelSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carModel")
public class CarModelController {

    private CarModelSevice carModelSevice;

    @Autowired
    public CarModelController(CarModelSevice carModelSevice) {
        this.carModelSevice = carModelSevice;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CarModel> find(@RequestParam()String brand){
        return carModelSevice.find(brand);
    }
}
