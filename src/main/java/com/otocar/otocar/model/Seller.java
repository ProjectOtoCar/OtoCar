package com.otocar.otocar.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.otocar.otocar.enums.TypeAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Accounts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 4, max = 40)
    private String firstName;
    @Size(min = 4, max = 40)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeAccount type;
    @Pattern(regexp = "[0-9]{9}")
    private String phoneNumber;
    @NotNull
    private LocalDate createAccount;
    @NotNull
    private LocalDate premiumAccount;
    private LocalDate lastAddvertisement;
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "seller")
    @JsonIgnore
    private Set<Advertisement> advertisement;


    public Seller() {
    }

    public Seller(String firstName,
                  String lastName,
                  TypeAccount type,
                  String phoneNumber,
                  Set<Advertisement> advertisement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.createAccount = LocalDate.now();
        this.premiumAccount = LocalDate.now();
        this.advertisement = advertisement;
        this.lastAddvertisement = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TypeAccount getType() {
        return type;
    }

    public void setType(TypeAccount type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(LocalDate createAccount) {
        this.createAccount = createAccount;
    }

    public Set<Advertisement> getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Set<Advertisement> advertisement) {
        this.advertisement = advertisement;
    }

    public LocalDate getPremiumAccount() {
        return premiumAccount;
    }

    public void setPremiumAccount(LocalDate premiumAccount) {
        this.premiumAccount = premiumAccount;
    }

    public LocalDate getLastAddvertisement() {
        return lastAddvertisement;
    }

    public void setLastAddvertisement(LocalDate lastAddvertisement) {
        this.lastAddvertisement = lastAddvertisement;
    }
}
