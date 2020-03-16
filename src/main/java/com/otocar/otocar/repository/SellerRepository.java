package com.otocar.otocar.repository;

import com.otocar.otocar.model.Seller;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SellerRepository  extends PagingAndSortingRepository<Seller, Long> {
}
