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

    Page<Advertisement> findAllByActiveAndPriceLessThan(boolean active, Pageable pageable,BigDecimal price);

    Page<Advertisement> findAllByActiveAndPriceBetween(boolean active,Pageable pageable, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndPriceGreaterThan(boolean active, Pageable pageable,BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_Model(boolean active, Pageable pageable, String brand, String model);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionLessThan(boolean active, Pageable pageable, String brand, int first);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionGreaterThan(boolean active, Pageable pageable, String brand, int first);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionBetween(boolean active, Pageable pageable, String brand, int low, int high);

    Page<Advertisement> findAllByActiveAndCar_BrandAndPriceLessThan(boolean active, Pageable pageable ,String brand, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndPriceGreaterThan(boolean active, Pageable pageable ,String brand, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndPriceBetween(boolean active, Pageable pageable ,String brand,BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionLessThanAndPriceLessThan(boolean active, Pageable pageable, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionLessThanAndPriceGreaterThan(boolean active, Pageable pageable, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionLessThanAndPriceBetween(boolean active, Pageable pageable, int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionGreaterThanAndPriceLessThan(boolean active, Pageable pageable, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionGreaterThanAndPriceGreaterThan(boolean active, Pageable pageable, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionGreaterThanAndPriceBetween(boolean active, Pageable pageable, int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionBetweenAndPriceLessThan(boolean active, Pageable pageable, int low, int high, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionBetweenAndPriceGreaterThan(boolean active, Pageable pageable,int low, int high, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionBetweenAndPriceBetween(boolean active, Pageable pageable, int low, int high, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThan(boolean active, Pageable pageable, String brand, String model, int first);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionGreaterThan(boolean active, Pageable pageable, String brand, String model, int first);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionBetween(boolean active, Pageable pageable, String brand, String model, int low, int high);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndPriceBetween(boolean active, Pageable pageable, String brand, String model, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceBetween(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionGreaterThanAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionGreaterThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionGreaterThanAndPriceBetween(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionLessThanAndPriceLessThan(boolean active, Pageable pageable, String brand, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionLessThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionLessThanAndPriceBetween(boolean active, Pageable pageable, String brand, int first, BigDecimal lowPrice, BigDecimal highPrice);


}
