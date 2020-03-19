package com.otocar.otocar.controller;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/seller")
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("")
    public Page<Seller> findAll(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String firstName,
                                @RequestParam(required = false) String lastName,
                                @RequestParam(required = false) String type,
                                @RequestParam(required = false) String premium,
                                @RequestParam(defaultValue = "") String sort){
        boolean isSort = false;
        TypeAccount typeAccount = null;
        if(sort.length() > 0){
            isSort = true;
        }
        if(type != null) {
            try {
                typeAccount = TypeAccount.valueOf(type);
            } catch (Exception e) {
                typeAccount = null;
            }
        }
        return sellerService.findAllByNameAndTypeAndPremium(firstName, lastName,typeAccount, isSort, premium, page);
    }
}
