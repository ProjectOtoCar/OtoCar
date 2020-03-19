package com.otocar.otocar.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Advertisements")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 100000000)
    private BigDecimal price;
    @NotNull
    private LocalDate dateAdd;
    @NotNull
    private Boolean active;
    @NotNull
    @Size(max = 100,min = 10)
    private String title;
    private String content;
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private Car car;
    @ManyToOne
    @NotNull
    private Seller seller;
    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL)
    private Set<Image> images;
    @ManyToOne
    @NotNull
    private City city;

    public Advertisement(BigDecimal price,
                         LocalDate dateAdd,
                         Boolean active,
                         String title,
                         String content,
                         City city,
                         Car car,
                         Seller seller,
                         Set<Image> images) {
        this.price = price;
        this.dateAdd = dateAdd;
        this.active = active;
        this.title = title;
        this.content = content;
        this.city = city;
        this.car = car;
        this.seller = seller;
        this.images = images;
    }

    public Advertisement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(LocalDate dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
