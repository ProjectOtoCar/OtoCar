package com.otocar.otocar.service;

import com.otocar.otocar.model.CarModel;
import com.otocar.otocar.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {

    private CarModel carModel;
    private CarModelRepository carModelRepository;

    @Autowired
    public CarModelService(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public List<CarModel>find(String brandName){
        List<CarModel> all = carModelRepository.findAll(brandName);
        return all;
    }
}
