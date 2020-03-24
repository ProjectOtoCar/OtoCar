package com.otocar.otocar.service;

import com.otocar.otocar.model.City;
import com.otocar.otocar.repository.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CityService extends AddPagable implements CrudServce<Long, City> {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    
    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    public Page<City> findAll(int page) {
        Pageable pageable = pagable(page);
        return cityRepository.findAll(pageable);
    }

    @Override
    public City findById(Long aLong) {
        return cityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Optional<Void> deleteById(Long aLong) {
        cityRepository.deleteById(aLong);
        return Optional.empty();
    }

    @Override
    public City save(City city) {
        Optional<City> cityOptional = Optional.of(cityRepository.save(city));
        return cityOptional.orElse(null);
    }

    @Override
    public City change(Long aLong, City obj) {
        if(cityRepository.findById(aLong).isEmpty()) {
            return cityRepository.save(obj);
        }
        obj.setId(aLong);
        return cityRepository.save(obj);
    }

    @Override
    public Optional<Void> patch(Long aLong, Map<String, Object> fields) {
        boolean isEdit = false;
        Optional<City> optionalCity = cityRepository.findById(aLong);
        if(optionalCity.isEmpty()) {
            return Optional.empty();
        }
        if(fields.size() == 0){
            return Optional.empty();
        }
        if(fields.get("name") != null) {
            optionalCity.get().setName((String)fields.get("name"));
            isEdit = true;
        }
        if(isEdit) {
            cityRepository.save(optionalCity.get());
        }
        return Optional.empty();
    }
}
