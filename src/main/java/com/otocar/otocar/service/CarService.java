package com.otocar.otocar.service;

import com.otocar.otocar.enums.Color;
import com.otocar.otocar.enums.TypeCar;
import com.otocar.otocar.enums.TypeFuel;
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
    public Car findById(Long aLong) {
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
    public Optional<Void> patch(Long aLong, Map<String, Object> fields) {
        boolean isEdit = false;
        Optional<Car> optionalCar = carRepository.findById(aLong);
        if(optionalCar.isEmpty()) {
            return Optional.empty();
        }
        if(fields.size() == 0){
            return Optional.empty();
        }
        if(fields.get("engine") != null){
            optionalCar.get().setEngine(Integer.parseInt((String)fields.get("engine")));
            isEdit = true;
        }
        if(fields.get("enginePower") != null){
            optionalCar.get().setEnginePower(Integer.parseInt((String)fields.get("enginePower")));
            isEdit = true;
        }

        if(fields.get("fuel") != null){
            optionalCar.get().setFuel(TypeFuel.valueOf((String) fields.get("fuel")));
            isEdit = true;
        }
        if(fields.get("firstRegistartion") != null){
            optionalCar.get().setFirstRegistartion(Integer.parseInt((String) fields.get("firstRegistartion")));
            isEdit = true;
        }
        if(fields.get("mileage") != null){
            optionalCar.get().setMileage(Integer.parseInt((String) fields.get("mileage")));
            isEdit = true;
        }
        if(fields.get("typeCar") != null){
            optionalCar.get().setTypeCar(TypeCar.valueOf((String) fields.get("typeCar")));
            isEdit = true;
        }
        if(fields.get("color") != null){
            optionalCar.get().setColor(Color.valueOf((String) fields.get("color")));
            isEdit = true;
        }
        if(isEdit) {
            carRepository.save(optionalCar.get());
        }
        return Optional.empty();
    }
}
