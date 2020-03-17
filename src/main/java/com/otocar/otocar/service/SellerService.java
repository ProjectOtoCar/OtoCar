package com.otocar.otocar.service;

import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {

    private SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller save(Seller seller) {
        Optional<Seller> sellerOptional = Optional.of(sellerRepository.save(seller));
        return sellerOptional.orElse(null);
    }
    public Iterable<Seller> findAll() {
        return sellerRepository.findAll();
    }


}
