package com.otocar.otocar.controller;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping("api/seller")
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public Page<Seller> findAll(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String firstName,
                                @RequestParam(required = false) String lastName,
                                @RequestParam(required = false) String type,
                                @RequestParam(required = false) String premium,
                                @RequestParam(defaultValue = "") String sort){

        return sellerService.findAllByNameAndTypeAndPremium(firstName, lastName,type, sort, premium, page);
    }
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Seller getById(@PathVariable(value = "id") Long id) {
        return sellerService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Seller postSeller(@RequestBody Seller seller) {
        seller.setType(TypeAccount.NORMAL);
        seller.setLastAddvertisement(null);
        seller.setAdvertisement(new HashSet<>());
        return sellerService.save(seller);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Seller putSeller(@PathVariable(value = "id") Long id, @RequestBody Seller seller) {
        return sellerService.change(id, seller);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable(value = "id") Long id) {
        sellerService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(value =  HttpStatus.OK)
    public void patch(@PathVariable(value = "id") Long id, @RequestBody Map<String, Object> fields ) {
        sellerService.patch(id, fields);
    }

    @PatchMapping("/premium/{id}")
    ResponseEntity<?> buyPremium(@PathVariable Long id, @RequestParam(defaultValue = "0") int days){
        sellerService.addPremium(id,days+1);
        return ResponseEntity.ok().build();
    }


}
