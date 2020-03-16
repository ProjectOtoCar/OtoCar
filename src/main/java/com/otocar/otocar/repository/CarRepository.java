package com.otocar.otocar.repository;

import com.otocar.otocar.model.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository  extends PagingAndSortingRepository<Car, Long> {
}
