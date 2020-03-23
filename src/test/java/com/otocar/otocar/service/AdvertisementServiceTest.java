package com.otocar.otocar.service;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.*;
import com.otocar.otocar.repository.AdvertisementRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;
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
    void should_find_all_Advertisement() {

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
    void should_patch_not_Change() {
        Map<String, Object> fields = new HashMap<>();
        Advertisement adv = new Advertisement();
        adv.setId(1L);

        given(advertisementRepository.findById(anyLong())).willReturn(Optional.of(adv));
        advertisementService.patch(1L,fields);

        then(advertisementRepository).should().findById(anyLong());
        then(advertisementRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(0);
    }

    @Test
    void patch(){
        Map<String, Object> fields = new HashMap<>();
        Advertisement adv = new Advertisement();
        adv.setId(1L);
        adv.setTitle("Test");
        fields.put("Title2", "Test2");
        Advertisement saveAdv = new Advertisement();
        saveAdv.setId(1L);
        saveAdv.setTitle("Test2");

        given(advertisementRepository.findById(anyLong())).willReturn(Optional.of(adv));
        advertisementService.patch(1L,fields);

        then(advertisementRepository).should().findById(anyLong());
        then(advertisementRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }

    @Test
    void should_delete_Advertisement_By_Id() {

        advertisementService.deleteById(3L);

        then(advertisementRepository).should().deleteById(3L);
        then(advertisementRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_Change_Advertisement() {
        final Long newId = 2L;
        Advertisement adv = new Advertisement();
        Advertisement changeAdv = new Advertisement();
        adv.setId(1L);
        changeAdv.setId(newId);
        given(advertisementRepository.findById(anyLong())).willReturn(Optional.of(adv));
        given(advertisementRepository.save(changeAdv)).willReturn(changeAdv);

        Advertisement foundAdv = advertisementService.change(newId, changeAdv);

        then(advertisementRepository).should().findById(anyLong());
        then(advertisementRepository).should().save(any(Advertisement.class));
        then(advertisementRepository).shouldHaveNoMoreInteractions();

        assertThat(foundAdv).isNotNull();
        assertThat(foundAdv.getId()).isEqualTo(newId);
    }


    static Stream<Arguments> getArgsToPatchChange() {
        BigDecimal price = BigDecimal.valueOf(10_000);
        LocalDate data = LocalDate.now();
        boolean isActive = true;
        String title ="Test";
        String content = "TestTestTestContentTest";
        Long id = 5L;
        Advertisement adv = new Advertisement(price,data,isActive,title,content,null,null,null,null);
       adv.setId(id);
        return Stream.of(Arguments.of(adv,"price",price,id),
            Arguments.of(adv,"content",content,id),
            Arguments.of(adv,"title",title,id)
        );
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgsToPatchChange")
    void patchChange(Advertisement adv,String key, Object value, Long id) {
        Map<String, Object> fields = new HashMap<>();
        fields.put(key,value);
        given(advertisementRepository.findById(anyLong())).willReturn(Optional.of(adv));
        given(advertisementRepository.save(any(Advertisement.class))).willReturn(adv);
        advertisementService.patch(id,fields);

        then(advertisementRepository).should().findById(anyLong());
        then(advertisementRepository).should().save(any(Advertisement.class));
        then(advertisementRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
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

    @Test
    void should_not_find_Advertisement_By_Id(){
        Advertisement adv = new Advertisement();
        adv.setId(1L);
        given(advertisementRepository.findById(anyLong())).willReturn(Optional.empty());

        Advertisement foundAdv = advertisementService.findAllById(1L);

        then(advertisementRepository).should().findById(1L);
        then(advertisementRepository).shouldHaveNoMoreInteractions();
        assertThat(foundAdv).isNull();
    }


}
