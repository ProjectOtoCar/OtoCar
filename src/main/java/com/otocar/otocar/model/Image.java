package com.otocar.otocar.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "Images")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String photo;
    private boolean isMainImage;

    @ManyToOne
    private Advertisement advertisement;

    public Image() {
    }

    public Image(String photo, boolean isMainImage, Advertisement advertisement) {
        this.photo = photo;
        this.isMainImage = isMainImage;
        this.advertisement = advertisement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isMainImage() {
        return isMainImage;
    }

    public void setMainImage(boolean mainImage) {
        isMainImage = mainImage;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
