package com.otocar.otocar.service;

import com.otocar.otocar.model.Brand;
import com.otocar.otocar.repository.BrandRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Map;
import java.util.Optional;

public class BrandService implements CrudServce<Long, Brand> {

    private  final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Iterable<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Long aLong) {
        return brandRepository.findById(aLong).orElse(null);
    }

    @Override
    public Optional<Void> deleteById(Long aLong) {
        brandRepository.deleteById(aLong);
        return Optional.empty();
    }

    @Override
    public Brand save(Brand obj) {
        Optional<Brand> optionalBrand = Optional.of(brandRepository.save(obj));
        return optionalBrand.orElse(null);
    }

    @Override
    public Brand change(Long aLong, Brand obj) {
        if(brandRepository.findById(aLong).isEmpty()) {
            return brandRepository.save(obj);
        }
        obj.setId(aLong);
        return brandRepository.save(obj);
    }

    @Override
    public Optional<Void> patch(Long aLong, Map<String, String> fields) {
        return Optional.empty();
    }
}
