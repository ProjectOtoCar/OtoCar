package com.otocar.otocar.repository;

import com.otocar.otocar.model.Car;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository  extends PagingAndSortingRepository<Car, Long> {
}
