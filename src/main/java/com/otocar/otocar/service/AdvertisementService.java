package com.otocar.otocar.service;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }


    public Page<Advertisement> findAll(int page) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        return advertisementRepository.findAllByActiveOrderByDateAddDesc(true, pag);
    }

    public Page<Advertisement> findByCity(int page, String city) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisement = Optional.of(advertisementRepository.findAllByActiveAndCityOrderByDateAddDesc(true, city, pag));
        return optionalAdvertisement.orElse(null);
    }

    public Page<Advertisement> findBySeller(int page, Seller seller) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisement = Optional.of(advertisementRepository.findAllByActiveAndSellerOrderByDateAddDesc(true, seller, pag));
        return optionalAdvertisement.orElse(null);

    }

    public Page<Advertisement> findByPriceAsc(int page) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveOrderByPriceAsc(true, pag));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByPriceDesc(int page) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveOrderByPriceDesc(true, pag));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByDataAddAsc(int page) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveOrderByDateAddAsc(true, pag));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandName(int page, String brandName) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_Name(true, pag, brandName));
        return optionalAdvertisements.orElse(null);
    }
public Page<Advertisement> findByCarRegistrationLessThan(int page, int year){
    Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
    Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationLessThan(true,pag,year);
    return optionalAdvertisements.orElse(null);
}

    public Page<Advertisement> findByCarRegistrationGreaterThan(int page, int year) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationGreaterThan(true, pag, year));
        return optionalAdvertisements.orElse(null);
    }
    public Page<Advertisement> findByCarRegistrationBetween(int page, int lowYear, int highYear){
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationBetween(true,pag,lowYear,highYear));
        return optionalAdvertisements.orElse(null);
    }
    public Page<Advertisement> findByPriceLessThan(int page, BigDecimal price){
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndPriceLessThan(true,pag,price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByPriceGreaterThan(int page, BigDecimal price){
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndPriceGreaterThan(true, pag, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByPriceBetween(int page, BigDecimal lowPrice, BigDecimal highPrice){
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndPriceBetween(true,pag,lowPrice,highPrice));
        return optionalAdvertisements.orElse(null);
    }

    public Advertisement save (Advertisement adv){
        return advertisementRepository.save(adv);
    }
}
