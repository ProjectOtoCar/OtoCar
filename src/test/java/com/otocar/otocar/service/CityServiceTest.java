package com.otocar.otocar.service;

import com.otocar.otocar.model.City;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    CityService cityService;

    final String name = "city";
    final Long id = 1L;
    City city;
    @BeforeEach
    void setUp() {
        city = new City();
        city.setName(name);
        city.setId(id);
    }

    @Test
    void findById() {
        given(cityRepository.findById(anyLong())).willReturn(Optional.of(city));

        City foundCity = cityService.findById(anyLong());

        then(cityRepository).should().findById(anyLong());
        then(cityRepository).shouldHaveNoMoreInteractions();
        assertThat(foundCity).isNotNull();
        assertThat(foundCity.getName()).isEqualTo(name);
        assertThat(foundCity.getId()).isEqualTo(id);
    }
    @Test
    void findByIdNotFound() {
        given(cityRepository.findById(anyLong())).willReturn(Optional.empty());

        City cityFound = cityService.findById(anyLong());

        then(cityRepository).should().findById(anyLong());
        then(cityRepository).shouldHaveNoMoreInteractions();
        assertThat(cityFound).isNull();
    }

    @Test
    void deleteById() {
        cityService.deleteById(anyLong());

        then(cityRepository).should().deleteById(anyLong());
        then(cityRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void save() {
        lenient().when(cityRepository.save(city)).thenReturn(city);
        City cityFound = cityService.save(city);

        then(cityRepository).should().save(any(City.class));
        then(cityRepository).shouldHaveNoMoreInteractions();
        assertThat(cityFound).isNotNull();
        assertThat(cityFound.getName()).isEqualTo(name);
    }

    @Test
    void changeObjectExist() {
        given(cityRepository.findById(id)).willReturn(Optional.of(city));
        given(cityRepository.save(any(City.class))).willReturn(city);

        City changeCity = cityService.change(id,city);

        then(cityRepository).should().findById(anyLong());
        then(cityRepository).should().save(any(City.class));
        then(cityRepository).shouldHaveNoMoreInteractions();

        assertThat(changeCity).isNotNull();
        assertThat(changeCity.getId()).isEqualTo(id);
    }
    @Test
    void changeObjectNotExist() {
        given(cityRepository.findById(id)).willReturn(Optional.empty());
        given(cityRepository.save(any(City.class))).willReturn(city);

        City changeCity = cityService.change(id,city);

        then(cityRepository).should().findById(anyLong());
        then(cityRepository).should().save(any(City.class));
        then(cityRepository).shouldHaveNoMoreInteractions();

        assertThat(changeCity).isNotNull();
        assertThat(changeCity.getId()).isEqualTo(id);
    }

    @Test
    void patchNotChange() {
        Map<String, Object> fields = new HashMap<>();
        given(cityRepository.findById(anyLong())).willReturn(Optional.of(city));
        cityService.patch(id,fields);

        then(cityRepository).should().findById(anyLong());
        then(cityRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(0);
    }
    @Test
    void patchWithWrongKey() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("test121321","sdasdsad");
        given(cityRepository.findById(anyLong())).willReturn(Optional.of(city));
        cityService.patch(id,fields);

        then(cityRepository).should().findById(anyLong());
        then(cityRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }
    static Stream<Arguments> getArgsToPatchChange() {
        return Stream.of(Arguments.of("name", "name"));
    }
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgsToPatchChange")
    void patchChange(String key, Object value) {
        Map<String, Object> fields = new HashMap<>();
        fields.put(key,value);
        given(cityRepository.findById(anyLong())).willReturn(Optional.of(city));
        cityService.patch(id,fields);

        then(cityRepository).should().findById(anyLong());
        then(cityRepository).should().save(any(City.class));
        then(cityRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }

    @Nested
    class FindAll {
        List<City> cityList;
        City city2;
        final String name2 = "name2";
        final Long id2 = 2L;
        @BeforeEach
        void setUp() {
            cityList =  new ArrayList<>();
            city2 = new City(name2, null);
            city2.setId(id2);
            cityList.add(city);
            cityList.add(city2);
        }
        @Test
        void findAllWithOutPagable() {
            given(cityRepository.findAll()).willReturn(cityList);

            Iterable<City> foundCities = cityService.findAll();
            assertThat(foundCities).isNotNull();

            List<City> foundCitiesList = new ArrayList<>();
            foundCities.forEach(foundCitiesList::add);

            then(cityRepository).should().findAll();
            then(cityRepository).shouldHaveNoMoreInteractions();

            assertThat(foundCitiesList.size()).isEqualTo(cityList.size());
            assertThat(foundCitiesList.get(0).getId()).isEqualTo(id);
            assertThat(foundCitiesList.get(1).getId()).isEqualTo(id2);
        }

        @Test
        void testFindAllWithPagable() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<City> cityPage = new PageImpl<>(cityList, pageable, cityList.size());
            given(cityRepository.findAll(pageable)).willReturn(cityPage);

            Iterable<City> foundCities = cityService.findAll();
            assertThat(foundCities).isNotNull();

            List<City> foundCitiesList = new ArrayList<>();
            foundCities.forEach(foundCitiesList::add);

            then(cityRepository).should().findAll(pageable);
            then(cityRepository).shouldHaveNoMoreInteractions();

            assertThat(foundCitiesList.size()).isEqualTo(cityList.size());
            assertThat(foundCitiesList.get(0).getId()).isEqualTo(id);
            assertThat(foundCitiesList.get(1).getId()).isEqualTo(id2);
        }
    }
}
