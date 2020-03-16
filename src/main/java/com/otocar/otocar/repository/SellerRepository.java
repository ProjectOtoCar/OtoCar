package com.otocar.otocar.repository;

import com.otocar.otocar.model.Seller;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository  extends PagingAndSortingRepository<Seller, Long> {
}
