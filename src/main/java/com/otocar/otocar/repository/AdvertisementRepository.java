package com.otocar.otocar.repository;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.Image;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdvertisementRepository  extends PagingAndSortingRepository<Advertisement, Long> {
}
