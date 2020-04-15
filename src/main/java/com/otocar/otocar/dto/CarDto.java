package com.otocar.otocar.dto;

import com.otocar.otocar.enums.Color;
import com.otocar.otocar.enums.TypeCar;
import com.otocar.otocar.enums.TypeFuel;

public class CarDto {
    private Long id;
    private Long brandId;
    private int engine;
    private int enginePower;
    private Long modelId;
    private TypeFuel fuel;
    private int firstRegistration;
    private int mileage;
    private TypeCar typeCar;
    private Color color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public TypeFuel getFuel() {
        return fuel;
    }

    public void setFuel(TypeFuel fuel) {
        this.fuel = fuel;
    }

    public int getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(int firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeCar typeCar) {
        this.typeCar = typeCar;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
