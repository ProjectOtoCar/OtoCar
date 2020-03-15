package com.otocar.otocar.model;

import com.otocar.otocar.enums.TypeAccount;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Accounts")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private TypeAccount type;
    private int phoneNumber;
    private LocalDate createAccount;
    private LocalDate premiumAccount;
    private LocalDate lastAddvertisement;
    @OneToMany(mappedBy = "seller")
    private Set<Advertisement> advertisement;


    public Seller() {
    }

    public Seller(String firstName,
                  String lastName,
                  TypeAccount type,
                  int phoneNumber,
                  LocalDate createAccount,
                  LocalDate premiumAccount,
                  Set<Advertisement> advertisement,
                  LocalDate lastAddvertisement) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.createAccount = createAccount;
        this.premiumAccount = premiumAccount;
        this.advertisement = advertisement;
        this.lastAddvertisement = lastAddvertisement;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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
