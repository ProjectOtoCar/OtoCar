package com.otocar.otocar.service;

import com.otocar.otocar.model.*;
import com.otocar.otocar.repository.AdvertisementRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class AdvertisementServiceTest {

    @Mock
    AdvertisementRepository advertisementRepository;
    @InjectMocks
    AdvertisementService advertisementService;


    @Test
    void should_find_all_advertisment() {

    }

    @Test
    void should_Add_Advertisement() {
        //Given
        Advertisement advertisement = new Advertisement();
        given(advertisementRepository.save(advertisement)).willReturn(advertisement);
        //when
        Advertisement adv = advertisementService.addAdv(advertisement);
        //then
        then(advertisementRepository).should().save(any(Advertisement.class));
        assertThat(adv).isEqualTo(advertisement);
        assertThat(adv).isNotNull();
    }


    @Test
    void patch() {
    }

    @Test
    void should_delete_Advertisement_By_Id() {

        advertisementService.deleteById(3L);

        then(advertisementRepository).should().deleteById(3L);
        then(advertisementRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void change() {
    }

    @Test
    void findAllAdvertisementBySeller() {
    }

    @Test
    void should_find_Advertisement_By_Id() {
        Advertisement adv = new Advertisement();
        adv.setId(1L);
        given(advertisementRepository.findById(1L)).willReturn(Optional.of(adv));
        Advertisement foundAdv = advertisementService.findAllById(1L);

        then(advertisementRepository).should().findById(1L);
        then(advertisementRepository).shouldHaveNoMoreInteractions();
        assertThat(foundAdv).isNotNull();
        assertThat(foundAdv.getId() == 1L);
    }


}
