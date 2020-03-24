package com.otocar.otocar.service;

import com.otocar.otocar.model.CarModel;
import com.otocar.otocar.repository.CarModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CarModelServiceTest {

    @Mock
    CarModelRepository carModelRepository;

    @InjectMocks
    CarModelService carModelService;

    final String brandName = "brand";
    CarModel carModel;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        carModel = new CarModel();
        carModel.setId(id);
    }

    @Test
    void find() {
        List<CarModel> carModelList = new ArrayList<>();
        CarModel carModel1 = new CarModel();
        carModelList.add(carModel);
        carModelList.add(carModel1);
        given(carModelRepository.findAll(anyString())).willReturn(carModelList);

        List<CarModel> carModelListFound = carModelService.find(anyString());

        then(carModelRepository).should().findAll(anyString());
        then(carModelRepository).shouldHaveNoMoreInteractions();

        assertThat(carModelListFound.size()).isEqualTo(carModelList.size());
    }
}