package com.otocar.otocar.repository;

import com.otocar.otocar.model.CarModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository extends CrudRepository<CarModel, Long> {

    @Query(name = "CarModel.findCarModel")
    List<CarModel> findAll(@Param("brandName")String brandName);


}
