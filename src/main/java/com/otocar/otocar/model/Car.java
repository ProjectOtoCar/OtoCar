package com.otocar.otocar.model;

import com.otocar.otocar.enums.Color;
import com.otocar.otocar.enums.TypeCar;
import com.otocar.otocar.enums.TypeFuel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private int engine;
    private int enginePower;
    private String model;
    @Enumerated(EnumType.STRING)
    private TypeFuel fuel;
    private int firstRegistartion;
    private int mileage;
    @Enumerated(EnumType.STRING)
    private TypeCar typeCar;
    @Enumerated(EnumType.STRING)
    private Color color;

    public Car(String brand,
               int engine,
               int enginePower,
               TypeFuel fuel,
               int firstRegistartion,
               int mileage,
               TypeCar typeCar,
               Color color,
               String model){
                this.brand = brand;
                this.engine = engine;
                this.enginePower = enginePower;
                this.fuel = fuel;
                this.firstRegistartion = firstRegistartion;
                this.mileage = mileage;
                this.typeCar = typeCar;
                this.color = color;
                this.model = model;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
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

    public TypeFuel getFuel() {
        return fuel;
    }

    public void setFuel(TypeFuel fuel) {
        this.fuel = fuel;
    }

    public int getFirstRegistartion() {
        return firstRegistartion;
    }

    public void setFirstRegistartion(int firstRegistartion) {
        this.firstRegistartion = firstRegistartion;
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


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
