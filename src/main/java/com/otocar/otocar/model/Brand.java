package com.otocar.otocar.model;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "Brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<CarModel> carModels;

    public Brand() {
    }

    public Brand(String name, Set<CarModel> carModels) {
        this.name = name;
        this.carModels = carModels;
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

    public Set<CarModels> getCarModels() {
        return carModels;
    }

    public void setCarModels(Set<CarModels> carModels) {
        this.carModels = carModels;
    }
}
