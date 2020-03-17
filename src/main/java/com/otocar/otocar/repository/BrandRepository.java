package com.otocar.otocar.repository;

import com.otocar.otocar.model.Brand;
import com.otocar.otocar.service.CrudServce;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudServce<Brand, Long> {
}
