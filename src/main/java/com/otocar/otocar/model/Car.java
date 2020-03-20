package com.otocar.otocar.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.otocar.otocar.enums.Color;
import com.otocar.otocar.enums.TypeCar;
import com.otocar.otocar.enums.TypeFuel;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Cars")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Brand brand;
    @Min(1)
    @Max(50_000)
    private int engine;
    @Min(1)
    @Max(20_000)
    private int enginePower;
    @ManyToOne
    @NotNull
    private CarModel model;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeFuel fuel;
    @Min(1900)
    @Max(2020)
    private int firstRegistration;
    @NotNull
    @Min(0)
    @Max(10_000_000)
    private int mileage;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeCar typeCar;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Color color;

    public Car() {
    }

    public Car(Brand brand, int engine, int enginePower, CarModel model, TypeFuel fuel, int firstRegistration, int mileage, TypeCar typeCar, Color color) {
        this.brand = brand;
        this.engine = engine;
        this.enginePower = enginePower;
        this.model = model;
        this.fuel = fuel;
        this.firstRegistration = firstRegistration;
        this.mileage = mileage;
        this.typeCar = typeCar;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public TypeFuel getFuel() {
        return fuel;
    }

    public void setFuel(TypeFuel fuel) {
        this.fuel = fuel;
    }

    public int getFirstRegistartion() {
        return firstRegistration;
    }

    public void setFirstRegistartion(int firstRegistartion) {
        this.firstRegistration = firstRegistartion;
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
