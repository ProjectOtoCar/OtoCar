package com.otocar.otocar.bootstrap;

import com.otocar.otocar.enums.Color;
import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.enums.TypeCar;
import com.otocar.otocar.enums.TypeFuel;
import com.otocar.otocar.model.*;
import com.otocar.otocar.service.AdvertisementService;
import com.otocar.otocar.service.BrandService;
import com.otocar.otocar.service.SellerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final SellerService sellerService;
    private final AdvertisementService advertisementService;
    private final BrandService brandService;

    public DataLoader(SellerService sellerService, AdvertisementService advertisementService, BrandService brandService) {
        this.sellerService = sellerService;
        this.advertisementService = advertisementService;
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(((Collection<Brand>)brandService.findAll()).size() != 0 && ((Collection<Seller>)sellerService.findAll()).size() == 0) {
            addData();
        }
    }

    private void addData() {
        Iterable<Brand> brands = brandService.findAll();
        List<Brand> brandList = new ArrayList<>();
        brands.forEach(brandList::add);
        Iterable<CarModel> models = brandList.get(0).getCarModels();
        List<CarModel> modelList = new ArrayList<>();
        models.forEach(modelList::add);


        Seller seller = new Seller("kura","kurowiak", TypeAccount.NORMAL,"999888777", LocalDate.now(),LocalDate.of(3000,5,5),new HashSet<Advertisement>(),LocalDate.of(2000,10,1));
        Car car = new Car(brandList.get(0), 1000, 1000,modelList.get(0), TypeFuel.PETROL, 2000, 200_000, TypeCar.HATCHBACK, Color.BIALY);
        Car car1 = new Car(brandList.get(1), 500, 1000,modelList.get(2), TypeFuel.GAS, 2010, 250_000, TypeCar.KOMBI, Color.NIEBIESKI);
        Car car2 = new Car(brandList.get(2), 1000, 1000,modelList.get(3), TypeFuel.DIESEL, 2020, 400_000, TypeCar.KABRIOLET, Color.POMARANCZOWY);
        Advertisement advertisement = new Advertisement(BigDecimal.valueOf(10000), LocalDate.now(), true, "title", "content", "city", car, seller , null );
        Advertisement advertisement1 = new Advertisement(BigDecimal.valueOf(1324300), LocalDate.now(), true, "title23", "content321", "citycity", car1, seller , null );
        Advertisement advertisement2 = new Advertisement(BigDecimal.valueOf(14230), LocalDate.now(), false, "title1", "co213ntent312321", "SPirng", car2, seller , null );
        seller.getAdvertisement().add(advertisement);
        seller.getAdvertisement().add(advertisement1);
        seller.getAdvertisement().add(advertisement2);
        sellerService.save(seller);

        Seller seller1 = new Seller("123456", "123456789", TypeAccount.ADMIN, "111111111", LocalDate.now(), LocalDate.now(), new HashSet<Advertisement>(),LocalDate.now());
        Car car3 = new Car(brandList.get(10), 10, 100, modelList.get(4), TypeFuel.PETROL, 1990, 20_000, TypeCar.SEDAN, Color.ZOLTY);
        Advertisement advertisement3 = new Advertisement(BigDecimal.valueOf(100), LocalDate.now(), false, "123456", "kjhgfdsa", "ziel", car3, seller, null);
        seller1.getAdvertisement().add(advertisement3);

        sellerService.save(seller1);

        Seller seller2 = new Seller("jdsnfsdn", "jaskdaslkfd", TypeAccount.MODERATOR, "123456789", LocalDate.of(2019,1,3),LocalDate.of(2021,1,1),new HashSet<Advertisement>(),LocalDate.of(2005,10,10));
        Car car4 = new Car((brandList.get(8)), 1000, 500, modelList.get(2), TypeFuel.GAS, 2005, 30_000, TypeCar.KABRIOLET, Color.BIALY);
        Advertisement advertisement4 = new Advertisement(BigDecimal.valueOf(100), LocalDate.now(), true, "title21321", "lalala", "kkk",car4, seller2,null);
        seller2.getAdvertisement().add(advertisement4);

        sellerService.save(seller2);
    }
}
