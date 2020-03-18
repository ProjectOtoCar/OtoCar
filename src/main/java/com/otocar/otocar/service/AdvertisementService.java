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
        return advertisementRepository.findAll(pag);
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

    public Page<Advertisement> findByCarRegistrationLessThan(int page, int year) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationLessThan(true, pag, year));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByCarRegistrationGreaterThan(int page, int year) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationGreaterThan(true, pag, year));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByCarRegistrationBetween(int page, int lowYear, int highYear) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationBetween(true, pag, lowYear, highYear));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByPriceLessThan(int page, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndPriceLessThan(true, pag, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByPriceGreaterThan(int page, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndPriceGreaterThan(true, pag, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByPriceBetween(int page, BigDecimal lowPrice, BigDecimal highPrice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndPriceBetween(true, pag, lowPrice, highPrice));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModel(int page, String brandName, String modelName) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_Name(true, pag, brandName, modelName));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndRegistrationLessThan(int page, String brandName, int year) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationLessThan(true, pag, brandName, year));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndRegistrationGreaterThan(int page, String brandName, int year) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationGreaterThan(true, pag, brandName, year));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndRegistrationBetween(int page, String brandName, int lowYear, int highYear) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationBetween(true, pag, brandName, lowYear, highYear));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndPriceLessThan(int page, String brandName, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndPriceLessThan(true, pag, brandName, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandandPriceGreaterThan(int page, String brandName, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndPriceGreaterThan(true, pag, brandName, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndPriceBetween(int page, String brandName, BigDecimal lowPrice, BigDecimal highPrice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndPriceBetween(true, pag, brandName, lowPrice, highPrice));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationLessAndPriceLess(int page, int firstRegistartion, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationLessThanAndPriceLessThan(true, pag, firstRegistartion, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationlessThanAndPriceGreaterThan(int page, int firstRegistration, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationLessThanAndPriceGreaterThan(true, pag, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationLessThanAndPriceBetween(int page, int firstRegistration, BigDecimal lowPrice, BigDecimal highPrice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationLessThanAndPriceBetween(true, pag, firstRegistration, lowPrice, highPrice));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationGreaterThanAndPriceLessThan(int page, int firstRegistration, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationGreaterThanAndPriceLessThan(true, pag, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationGreaterThanAndPriceGreaterThan(int page, int firstRegistration, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationGreaterThanAndPriceGreaterThan(true, pag, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationGreaterThanAndPriceBetween(int page, int firstRegistration, BigDecimal lowPrice, BigDecimal highPrice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationGreaterThanAndPriceBetween(true, pag, firstRegistration, lowPrice, highPrice));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationBetweenAndPriceLessThan(int page, int lowFirstRegistration, int highRegistration, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationBetweenAndPriceLessThan(true, pag, lowFirstRegistration, highRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationBetweenAndPriceGreaterThan(int page, int lowFirstRegistration, int highRegistration, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationBetweenAndPriceGreaterThan(true, pag, lowFirstRegistration, highRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByRegistrationBetweenAndPriceBetween(int page, int lowFirstRegistration, int highRegistration, BigDecimal lowPrice, BigDecimal highPrice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationBetweenAndPriceBetween(true, pag, lowFirstRegistration, highRegistration, lowPrice, highPrice));
        return optionalAdvertisements.orElse(null);

    }

    public Page<Advertisement> findByBrandAndModelAndRegistrationLessThan(int page, String brandName, String modelName, int firstRegistration) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationLessThan(true, pag, brandName, modelName, firstRegistration));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndRegistrationGreaterThan(int page, String brandName, String modelName, int firstRegistration) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationGreaterThan(true, pag, brandName, modelName, firstRegistration));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndRegistrationBetween(int page, String brandName, String modelName, int lowFirstRegistration, int highFirstRegistration) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationBetween(true, pag, brandName, modelName, lowFirstRegistration, highFirstRegistration));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndPriceLessThan(int page, String brandName, String modelName, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndPriceLessThan(true, pag, brandName, modelName, price));
        return optionalAdvertisements.orElse(null);

    }

    public Page<Advertisement> findByBrandAndModelAndPriceGreaterThan(int page, String brandName, String modelName, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndPriceGreaterThan(true, pag, brandName, modelName, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndPriceBetween(int page, String brandName, String modelName, BigDecimal lowPrice, BigDecimal highPice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndPriceBetween(true, pag, brandName, modelName, lowPrice, highPice));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndRegistrationLessThanAndPriceLessThan(int page, String brandName, int firstRegistration, BigDecimal price) {

        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationLessThanAndPriceLessThan(true, pag, brandName, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndRegistrationLessThanAndPriceGreaterThan(int page, String brandName, int firstRegistration, BigDecimal price) {

        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationLessThanAndPriceGreaterThan(true, pag, brandName, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndRegistrationLessThanAndPriceBetween(int page, String brandName, int firstRegistration, BigDecimal lowPrice, BigDecimal highPrice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationLessThanAndPriceBetween(true, pag, brandName, firstRegistration, lowPrice, highPrice));
        return optionalAdvertisements.orElse(null);
    }


    public Page<Advertisement> findByBrandAndModelAndRegistrationLessThanAndPriceLessThan(int page, String brandName, String modelName, int firstRegistration, BigDecimal price) {

        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationLessThanAndPriceLessThan(true, pag, brandName, modelName, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndRegistrationLessThanAndPriceGreaterThan(int page, String brandName, String modelName, int firstRegistration, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationLessThanAndPriceGreaterThan(true, pag, brandName, modelName, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndRegistrationLessThanAndPriceBetween(int page, String brandName, String modelName, int firstRegistration, BigDecimal lowePrice, BigDecimal highPrice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationLessThanAndPriceBetween(true, pag, brandName, modelName, firstRegistration, lowePrice, highPrice));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndRegistrationGreaterThanAndPriceLessThan(int page, String brandName, String modelName, int firstRegistration, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationGreaterThanAndPriceLessThan(true, pag, brandName, modelName, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndRegistrationGreaterThanAndPriceGreaterThan(int page, String brandName, String modelName, int firstRegistration, BigDecimal price) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationGreaterThanAndPriceGreaterThan(true, pag, brandName, modelName, firstRegistration, price));
        return optionalAdvertisements.orElse(null);
    }

    public Page<Advertisement> findByBrandAndModelAndRegistrationGreaterThanAndPriceBetween(int page, String brandName, String modelName, int firstRegistration, BigDecimal lowePrice, BigDecimal highPrice) {
        Pageable pag = PageRequest.of((page - 1) * 10, page * 10);
        Optional<Page<Advertisement>> optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationGreaterThanAndPriceBetween(true, pag, brandName, modelName, firstRegistration, lowePrice, highPrice));
        return optionalAdvertisements.orElse(null);
    }

    public Advertisement save(Advertisement adv) {
        return advertisementRepository.save(adv);
    }
}
