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
    Page<Advertisement> findAllByActive(boolean active, Pageable pageable);

    Page<Advertisement> findAllByActiveAndDateAddGreaterThan(boolean active, LocalDate dateAdd, Pageable pageable);

    Page<Advertisement> findAllByActiveAndCity(boolean active, String city, Pageable pageable);

    Page<Advertisement> findAllByActiveAndDateAddGreaterThanAndCity(boolean active, LocalDate dateAdd, String city, Pageable pageable);

    Page<Advertisement> findAllBySellerAndActive(Seller seller, boolean active, Pageable pageable);
    //Cena(Ograniczenie)

    //Sortowania
    Page<Advertisement> findAllByActiveAndOrderByPriceAsc(boolean active, Pageable pageable);

    Page<Advertisement> findAllByActiveAndOrderByPriceDesc(boolean active, Pageable pageable);

    Page<Advertisement> findAllByActiveandOrderByDateAddAsc(boolean active, Pageable pageable);

    Page<Advertisement> findAllByActiveandOrderByDateAddDesc(boolean active, Pageable pageable);
//----------------------------------------------------------------------------------------------------------------------------
    Page<Advertisement> findAllByActiveAndCar_Brand(boolean active, Pageable pageable, String brand);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionLessThan(boolean active, Pageable pageable, String brand, int first);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionGreaterThan(boolean active, Pageable pageable, String brand, int first);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionBetween(boolean active, Pageable pageable, String brand, String model, int low, int high);

    Page<Advertisement> findAllByActiveAndPriceLessThan(boolean active, BigDecimal price, Pageable pageable);

    Page<Advertisement> findAllByActiveAndPriceBetween(boolean active, BigDecimal low, BigDecimal high, Pageable pageable);

    Page<Advertisement> findAllByActiveAndPriceGreaterThan(boolean active, BigDecimal price, Pageable pageable);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_Model(boolean active, Pageable pageable, String brand, String model);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionLessTan(boolean active, Pageable pageable, String brand, int first);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionGreaterThan(boolean active, Pageable pageable, String brand, int first);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_FirstRegistartionBetween(boolean active, Pageable pageable, String brand, int low, int high);

    Page<Advertisement> findAllByActiveAndCar_BrandAndPriceLessThan(boolean active, Pageable pageable ,String brand, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndPriceGreaterThan(boolean active, Pageable pageable ,String brand, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndPriceBetween(boolean active, Pageable pageable ,String brand,BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionLessThanAndPriceLessThan(boolean active, Pageable pageable, String brand, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionLessThanAndPriceGreaterThan(boolean active, Pageable pageable,  String brand, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionLessThanAndPriceBetween(boolean active, Pageable pageable, String brand,int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionGreaterThanAndPriceLessThan(boolean active, Pageable pageable, String brand, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionGreaterThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionGreaterThanAndPriceBetween(boolean active, Pageable pageable, String brand, int first, BigDecimal low, BigDecimal high);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionBetweenAndPriceLessThan(boolean active, Pageable pageable, String brand, int first, int low, int high, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionBetweenAndPriceGreaterThan(boolean active, Pageable pageable, String brand, int first, int low, int high, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_FirstRegistartionBetweennAndPriceBetween(boolean active, Pageable pageable, String brand, int first, int low, int high, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThan(boolean active, Pageable pageable, String brand, String model, int first);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionGreaterThan(boolean active, Pageable pageable, String brand, String model, int first);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionBetween(boolean active, Pageable pageable, String brand, String model, int low, int high);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndPricenBetween(boolean active, Pageable pageable, String brand, String model, BigDecimal lowPrice, BigDecimal highPrice);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceBetween(boolean active, Pageable pageable, String brand, String model, int first, int low, int high);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionGreaterThanAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionGreaterThanAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, int first, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionGreaterThanAndPriceBetween(boolean active, Pageable pageable, String brand, String model, int first, int low, int high);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceBetweenAndPriceLessThan(boolean active, Pageable pageable, String brand, String model, int first, int low, int high, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceBetweenAndPriceGreaterThan(boolean active, Pageable pageable, String brand, String model, int first, int low, int high, BigDecimal price);

    Page<Advertisement> findAllByActiveAndCar_BrandAndCar_ModelAndCar_FirstRegistartionLessThanAndPriceBetweenAndPriceBetween(boolean active, Pageable pageable, String brand, String model, int first, int low, int high, BigDecimal lowPrice, BigDecimal highPrice);


}
