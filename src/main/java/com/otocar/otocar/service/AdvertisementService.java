package com.otocar.otocar.service;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class AdvertisementService extends AddPagable {

    private AdvertisementRepository advertisementRepository;


    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public Page<Advertisement> find(int page, String brandName, String modelName, int lowRegistration, int highRegistration, BigDecimal lowPrcie, BigDecimal highPrice, String orderBy) {
        Optional<Page<Advertisement>> optionalAdvertisements = null;
        if (brandName == null && orderBy.equals("asc")) {
            optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceAsc(true, pagable(page), lowRegistration, highRegistration, lowPrcie, highPrice));
        } else if (brandName == null) {
            optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceDesc(true, pagable(page), lowRegistration, highRegistration, lowPrcie, highPrice));
        } else if (modelName == null && orderBy.equals("asc")) {
            optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceAsc(true, pagable(page), brandName, lowRegistration, highRegistration, lowPrcie, highPrice));
        } else if (modelName == null) {
            optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceDesc(true, pagable(page), brandName, lowRegistration, highRegistration, lowPrcie, highPrice));
        } else if (orderBy.equals("asc")) {
            optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceAsc(true, pagable(page), brandName, modelName, lowRegistration, highRegistration, lowPrcie, highPrice));
        } else {
            optionalAdvertisements = Optional.of(advertisementRepository.findAllByActiveAndCar_Brand_NameAndCar_Model_NameAndCar_FirstRegistrationBetweenAndPriceBetweenOrderByPriceDesc(true, pagable(page), brandName, modelName, lowRegistration, highRegistration, lowPrcie, highPrice));
        }
        return optionalAdvertisements.orElse(null);
    }

    public Advertisement addAdv(Advertisement adv) {
        return advertisementRepository.save(adv);
    }

    public Optional<Void> patch(Long id, Map<String, Object> fields) {
        boolean isEdit = false;

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(id);


        if (optionalAdvertisement.isEmpty()) {
            return Optional.empty();
        }
        if (fields.size() == 0) {
            return Optional.empty();
        }
        if (fields.get("price") != null) {
            optionalAdvertisement.get().setPrice((BigDecimal) fields.get("Price"));
            isEdit = true;
        }
        if (fields.get("title") != null) {
            optionalAdvertisement.get().setTitle((String) fields.get("title"));
            isEdit = true;
        }
        if (fields.get("content") != null) {
            optionalAdvertisement.get().setContent((String) fields.get("content"));
            isEdit = true;
        }

        if (isEdit == true) {
            advertisementRepository.save(optionalAdvertisement.get());
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        advertisementRepository.deleteById(id);
    }


    public Advertisement change(Long aLong, Advertisement advertisement) {
        if (advertisementRepository.findById(aLong).isEmpty()) {
            return advertisementRepository.save(advertisement);
        }
        advertisementRepository.deleteById(aLong);
        advertisement.setId(aLong);
        return advertisementRepository.save(advertisement);
    }

    public Page<Advertisement> findAllAdvertisementBySeller(Long id, Integer page) {
        return advertisementRepository.findAllAdvertisementBySeller(id, pagable(page));
    }

    public Advertisement findAllById(Long id) {
        return advertisementRepository.findById(id).orElse(null);
    }
}
