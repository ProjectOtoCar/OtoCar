package com.otocar.otocar.service;

import com.otocar.otocar.model.Brand;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    BrandRepository brandRepository;

    @InjectMocks
    BrandService brandService;

    final Long id = 1L;
    final String name = "name";
    final String name2 = "name2";
    Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand();
        brand.setId(id);
        brand.setName(name);
    }

    @Test
    void findAll() {
        List<Brand> brandList = new ArrayList<>();
        Brand brand1 = new Brand();
        brand1.setName(name2);

        brandList.add(brand);
        brandList.add(brand1);

        given(brandRepository.findAll()).willReturn(brandList);

        Iterable<Brand> foundBrands = brandService.findAll();

        List<Brand> brandListTest = new ArrayList<>();

        foundBrands.forEach(brandListTest::add);

        then(brandRepository).should().findAll();
        then(brandRepository).shouldHaveNoMoreInteractions();

        assertThat(brandListTest.size()).isEqualTo(brandList.size());

        assertThat(brandListTest.get(0).getName()).isEqualTo(name);
        assertThat(brandListTest.get(1).getName()).isEqualTo(name2);
    }

    @Test
    void findById() {

        given(brandRepository.findById(id)).willReturn(Optional.of(brand));

        Brand foundBrand = brandService.findById(id);

        then(brandRepository).should().findById(id);
        then(brandRepository).shouldHaveNoMoreInteractions();
        assertThat(foundBrand).isNotNull();
        assertThat(foundBrand.getName()).isEqualTo(name);
        assertThat(foundBrand.getId()).isEqualTo(id);
    }

    @Test
    void findByIdNotFound() {

        given(brandRepository.findById(anyLong())).willReturn(Optional.empty());

       Brand brandFound = brandService.findById(id);

        then(brandRepository).should().findById(id);
        then(brandRepository).shouldHaveNoMoreInteractions();
        assertThat(brandFound).isNull();
    }

    @Test
    void deleteById() {
        brandService.deleteById(id);

        then(brandRepository).should().deleteById(id);
        then(brandRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void save() {
        given(brandRepository.save(any(Brand.class))).willReturn(brand);

        Brand foundBrand = brandService.save(brand);

        then(brandRepository).should().save(any(Brand.class));
        then(brandRepository).shouldHaveNoMoreInteractions();
        assertThat(foundBrand).isNotNull();
        assertThat(foundBrand.getName()).isEqualTo(name);
    }

    @Test
    void changeObjectExist() {
        Brand changeBrand = new Brand();
        changeBrand.setName(name2);
        changeBrand.setId(id);
        given(brandRepository.findById(id)).willReturn(Optional.of(brand));
        given(brandRepository.save(changeBrand)).willReturn(changeBrand);

        Brand testBrand = brandService.change(id, changeBrand);

        then(brandRepository).should().findById(anyLong());
        then(brandRepository).should().save(any(Brand.class));
        then(brandRepository).shouldHaveNoMoreInteractions();

        assertThat(testBrand).isNotNull();
        assertThat(testBrand.getName()).isEqualTo(name2);
        assertThat(testBrand.getId()).isEqualTo(id);
    }
    @Test
    void changeObjectNotExist() {
        final Long newId = 2L;
        Brand changeBrand = new Brand();
        changeBrand.setId(newId);
        changeBrand.setName(name);
        given(brandRepository.findById(anyLong())).willReturn(Optional.empty());
        given(brandRepository.save(changeBrand)).willReturn(changeBrand);

        Brand testBrand = brandService.change(newId, changeBrand);

        then(brandRepository).should().findById(anyLong());
        then(brandRepository).should().save(any(Brand.class));
        then(brandRepository).shouldHaveNoMoreInteractions();

        assertThat(testBrand).isNotNull();
        assertThat(testBrand.getName()).isEqualTo(name);
        assertThat(testBrand.getId()).isEqualTo(newId);
    }

    @Test
    void patchNotChange() {
        Map<String, Object> fields = new HashMap<>();
        given(brandRepository.findById(anyLong())).willReturn(Optional.of(brand));
        brandService.patch(id,fields);

        then(brandRepository).should().findById(anyLong());
        then(brandRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(0);
    }

    @Test
    void patchChange() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("name",name);
        given(brandRepository.findById(anyLong())).willReturn(Optional.of(brand));
        given(brandRepository.save(any(Brand.class))).willReturn(brand);
        brandService.patch(id,fields);

        then(brandRepository).should().findById(anyLong());
        then(brandRepository).should().save(any(Brand.class));
        then(brandRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }

    @Test
    void patchWithWrongKey() {
        Map<String, Object> fields = new HashMap<>();
        fields.put(name2, name2);
        Brand saveBrand = new Brand();
        saveBrand.setId(id);
        saveBrand.setName(name);
        given(brandRepository.findById(anyLong())).willReturn(Optional.of(brand));
        brandService.patch(id,fields);

        then(brandRepository).should().findById(anyLong());
        then(brandRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }
}