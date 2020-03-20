package com.otocar.otocar.service;

import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @Mock
    SellerRepository sellerRepository;

    @InjectMocks
    SellerService sellerService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findById() {
        final String firstName = "test";
        Seller seller = new Seller();
        seller.setId(1L);
        seller.setFirstName(firstName);
        given(sellerRepository.findById(1L)).willReturn(Optional.of(seller));

        Seller foundSeller = sellerService.findById(1L);

        then(sellerRepository).should().findById(1L);
        then(sellerRepository).shouldHaveNoMoreInteractions();
        assertThat(foundSeller).isNotNull();
        assertThat(foundSeller.getFirstName()).isEqualTo(firstName);


    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void change() {
    }

    @Test
    void patch() {
    }

    @Test
    void findAllByNameAndTypeAndPremium() {
    }
}