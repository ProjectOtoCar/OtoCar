package com.otocar.otocar.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Models")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Brand brand;
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Car> car;

    public CarModel() {
    }

    public CarModel(String name, Brand brand, Set<Car> car) {
        this.name = name;
        this.brand = brand;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }
}
