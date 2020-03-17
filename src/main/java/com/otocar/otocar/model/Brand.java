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
    private Set<CarModels> carModels;

}
