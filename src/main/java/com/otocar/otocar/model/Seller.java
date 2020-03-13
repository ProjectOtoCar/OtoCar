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
    @OneToMany(mappedBy = "seller")
    private Set<Advertisement> advertisement;

}
