package com.otocar.otocar.repository;

import com.otocar.otocar.model.CarModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends CrudRepository<CarModel, Long> {
}
