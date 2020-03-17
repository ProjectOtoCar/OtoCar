package com.otocar.otocar.service;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvertisementService {

    private AdvertisementRepository advertisementRepository;
    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }


   public Page<Advertisement> findAll(int page){
        Pageable pag = PageRequest.of((page-1)*10,page*10);
        return advertisementRepository.findAllByActiveOrderByDateAddDesc(true, pag);
    }

    public Page<Advertisement> findByCity(int page, String city){
       Pageable pag = PageRequest.of((page-1)*10,page*10);
        Optional<Page<Advertisement>> optionalAdvertisement = Optional.of(advertisementRepository.findAllByActiveAndCityOrderByDateAddDesc(true,city,pag));
        return optionalAdvertisement.orElse(null);
    }

    public Page<Advertisement> findBySeller(int page, Seller seller){
        Pageable pag = PageRequest.of((page-1)*10,page*10);
        Optional<Page<Advertisement>> optionalAdvertisement = Optional.of(advertisementRepository.findAllByActiveAndSellerOrderByDateAddDesc(true,seller,pag));
        return optionalAdvertisement.orElse(null);

    }


}
