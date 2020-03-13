package com.otocar.otocar.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Advertisements")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private LocalDate dateAdd;
    private String title;
    private String content;
    private String city;
    @OneToOne
    private Car car;
    @ManyToOne
    private Seller seller;

}
