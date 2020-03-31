package com.otocar.otocar.controller;

import com.otocar.otocar.enums.Color;
import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.enums.TypeCar;
import com.otocar.otocar.enums.TypeFuel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enums")
public class EnumsController {

    @GetMapping("/color")
    Color[] showColors(){
        return Color.values();
    }

    @GetMapping("/account")
    TypeAccount[] showTypeAccount(){
        return TypeAccount.values();
    }

    @GetMapping("/typeCar")
    TypeCar[] showCars(){
        return TypeCar.values();
    }

    @GetMapping("/typeFuel")
    TypeFuel[] showFuels(){
        return TypeFuel.values();
    }
}
