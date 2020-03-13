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
    private TypeFuel fuel;
    private LocalDate firstRegistartion;
    private int mileage;
    private TypeCar typeCar;
    private Color color;


}
