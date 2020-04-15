package com.otocar.otocar.dto;

import com.otocar.otocar.model.Image;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class AdvertisementDto {
    private Long id;
    private BigDecimal price;
    private LocalDate dateAdd;
    private Boolean active;
    private String title;
    private String content;
    private CarDto car;
    private Long sellerId;
    private Set<Image> images;
    private Long cityId;

    public AdvertisementDto() {
    }

    public AdvertisementDto(Long id, BigDecimal price, LocalDate dateAdd, Boolean active, String title, String content, CarDto car, Long sellerId, Set<Image> images, Long cityId) {
        this.id = id;
        this.price = price;
        this.dateAdd = dateAdd;
        this.active = active;
        this.title = title;
        this.content = content;
        this.car = car;
        this.sellerId = sellerId;
        this.images = images;
        this.cityId = cityId;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
