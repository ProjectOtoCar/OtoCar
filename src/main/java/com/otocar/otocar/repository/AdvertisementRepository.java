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
    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceDesc(boolean active, Pageable pageable,int lowFirst,int hightfirst, BigDecimal lowPrice, BigDecimal highPrice);
    Page<Advertisement> findAllByActiveAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceAsc(boolean active, Pageable pageable,int lowFirst,int hightfirst, BigDecimal lowPrice, BigDecimal highPrice);
    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceDesc(boolean active, Pageable pageable, String brandName,int lowFirst,int hightfirst, BigDecimal lowPrice, BigDecimal highPrice);
    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceAsc(boolean active, Pageable pageable, String brandName,int lowFirst,int hightfirst, BigDecimal lowPrice, BigDecimal highPrice);
    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceDesc(boolean active, Pageable pageable, String brandName,String modelName,int lowFirst,int hightfirst, BigDecimal lowPrice, BigDecimal highPrice);
    Page<Advertisement> findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceAsc(boolean active, Pageable pageable, String brandName,String modelName,int lowFirst,int hightfirst, BigDecimal lowPrice, BigDecimal highPrice);






}
