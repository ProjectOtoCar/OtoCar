package com.otocar.otocar.service;

import com.otocar.otocar.model.Car;
import com.otocar.otocar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CarService implements CrudServce<Long, Car> {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findBy(Long aLong) {
        return carRepository.findById(aLong).orElse(null);
    }

    @Override
    public Optional<Void> deleteById(Long aLong) {
        carRepository.deleteById(aLong);
        return Optional.empty();
    }

    @Override
    public Car save(Car car) {
        Optional<Car> optionalCar = Optional.of(carRepository.save(car));
        return  optionalCar.orElse(null);
    }

    @Override
    public Car change(Long aLong, Car obj) {
        if(carRepository.findById(aLong).isEmpty()){
            return carRepository.save(obj);
        }
        obj.setId(aLong);
        return carRepository.save(obj);
    }

    @Override
    public Optional<Void> patch(Long aLong, Map<String, String> fields) {
        return Optional.empty();
    }
}
