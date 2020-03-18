package com.otocar.otocar.service;

import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class SellerService extends AddPagable {

    private SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    public Seller findById(Long aLong) {
        return sellerRepository.findById(aLong).orElse(null);
    }

    public Page<Seller> findAll(int page) {
        Pageable pageable = pagable(page);
        return sellerRepository.findAll(pageable);
    }

    public Iterable<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Seller save(Seller seller) {
        Optional<Seller> sellerOptional = Optional.of(sellerRepository.save(seller));
        return sellerOptional.orElse(null);
    }

    public Optional<Void> deleteById(Long along) {
        sellerRepository.deleteById(along);
        return Optional.empty();
    }

    public Seller change(Long aLong, Seller seller) {
        if(sellerRepository.findById(aLong).isEmpty()) {
            return sellerRepository.save(seller);
        }
        seller.setId(aLong);
        return sellerRepository.save(seller);
    }

}
