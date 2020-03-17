package com.otocar.otocar.repository;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.Image;
import com.otocar.otocar.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface AdvertisementRepository extends PagingAndSortingRepository<Advertisement, Long> {
    Page<Advertisement> findAllByActiveOrderByDateAddDesc(boolean active, Pageable pageable);

    Page<Advertisement> findAllByActiveAndCityOrderByDateAddDesc(boolean active, String city, Pageable pageable);

    Page<Advertisement> findAllByActiveAndSellerOrderByDateAddDesc(boolean active, Seller seller, Pageable pageable);

    Page<Advertisement> findAllByActiveOrderByPriceAsc(boolean active, Pageable pageable);

    Page<Advertisement> findAllByActiveOrderByPriceDesc(boolean active, Pageable pageable);

    Page<Advertisement> findAllByActiveOrderByDateAddAsc(boolean active, Pageable pageable);


    //----------------------------------------------------------------------------------------------------------------------------
    Page<Advertisement> findAllByActiveAndCar_Brand_Name(boolean active, Pageable pageable, String brandName);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationLessThan(boolean active, Pageable pageable, int first);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationGreaterThan(boolean active, Pageable pageable, int first);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationBetween(boolean active, Pageable pageable, int low, int high);

    Page<Advertisement> findAllByActiveAndPriceLessThan(boolean active, Pageable pageable, BigDecimal price);

    Page<Advertisement> findAllByActiveAndPriceBetween(boolean active, Pageable pageable, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndPriceGreaterThan(boolean active, Pageable pageable, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_Name(boolean active, Pageable pageable, String brand, String model);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationLessThan(boolean active, Pageable pageable, String brand, int first);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationGreaterThan(boolean active, Pageable pageable, String brand, int first);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationBetween(boolean active, Pageable pageable, String brand, int low, int high);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndPriceLessThan(boolean active, Pageable pageable, String brand, BigDecimal price);


    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndPriceGreaterThan(boolean active, Pageable pageable, String brand, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndPriceBetween(boolean active, Pageable pageable, String brand, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationLessThanAndPriceLessThan(boolean active, Pageable pageable, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationLessThanAndPriceGreaterThan(boolean active, Pageable pageable, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationLessThanAndPriceBetween(boolean active, Pageable pageable, int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationGreaterThanAndPriceLessThan(boolean active, Pageable pageable, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationGreaterThanAndPriceGreaterThan(boolean active, Pageable pageable, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationGreaterThanAndPriceBetween(boolean active, Pageable pageable, int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationBetweenAndPriceLessThan(boolean active, Pageable pageable, int low, int high, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationBetweenAndPriceGreaterThan(boolean active, Pageable pageable, int low, int high, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationBetweenAndPriceBetween(boolean active, Pageable pageable, int low, int high, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationLessThan(boolean active, Pageable pageable, String brand, String model, int first);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationGreaterThan(boolean active, Pageable pageable, String brand, String model, int first);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationBetween(boolean active, Pageable pageable, String brand, String model, int low, int high);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndPriceBetween(boolean active, Pageable pageable, String brand, String model, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationLessThanAndPriceLessThan(boolean active, Pageable pageable, String brand, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationLessThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationLessThanAndPriceBetween(boolean active, Pageable pageable, String brand, int first, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationLessThanAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationLessThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationLessThanAndPriceBetween(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationGreaterThanAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationGreaterThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationGreaterThanAndPriceBetween(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal low, BigDecimal high);


}
