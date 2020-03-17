package com.otocar.otocar.repository;

import com.otocar.otocar.model.CarModel;
import com.otocar.otocar.service.CrudServce;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends CrudServce<CarModel, Long> {
}
