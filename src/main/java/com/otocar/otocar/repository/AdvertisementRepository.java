package com.otocar.otocar.repository;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.Image;
import com.otocar.otocar.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AdvertisementRepository  extends PagingAndSortingRepository<Advertisement, Long> {
    Page<Advertisement> findAllByActive(boolean active,Pageable pageable);
    Page<Advertisement> findAllByActiveAndDateAddGreaterThan(boolean active, LocalDate dateAdd, Pageable pageable);
    Page<Advertisement> findAllByActiveAndCity(boolean active, String city,Pageable pageable);
    Page<Advertisement> findAllByActiveAndDateAddGreaterThanAndCity(boolean active, LocalDate dateAdd, String city,Pageable pageable);
    Page<Advertisement> findAllBySellerAndActive(Seller seller, boolean active, Pageable pageable);
}
