package com.otocar.otocar.bootstrap;

import com.otocar.otocar.enums.Color;
import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.enums.TypeCar;
import com.otocar.otocar.enums.TypeFuel;
import com.otocar.otocar.model.*;
import com.otocar.otocar.service.BrandService;
import com.otocar.otocar.service.CityService;
import com.otocar.otocar.service.SellerService;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class DataLoader implements CommandLineRunner {

    private final SellerService sellerService;
    private final BrandService brandService;
    private final CityService cityService;

    public DataLoader(SellerService sellerService, BrandService brandService, CityService cityService) {
        this.sellerService = sellerService;
        this.brandService = brandService;
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(((Collection<Brand>)brandService.findAll()).size() != 0 &&
                ((Collection<Seller>)sellerService.findAll()).size() == 0 &&
                ((Collection<City>)cityService.findAll()).size() != 0) {
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

        Iterable<City> cities = cityService.findAll();
        List<City> cityList = new ArrayList<>();
        cities.forEach(cityList::add);


        Seller seller = new Seller("kura","kurowiak", TypeAccount.NORMAL,"999888777",new HashSet<Advertisement>());
        Car car = new Car(brandList.get(0), 1000, 1000,modelList.get(0), TypeFuel.PETROL, 2000, 200_000, TypeCar.HATCHBACK, Color.BIALY);
        Car car1 = new Car(brandList.get(1), 500, 1000,modelList.get(2), TypeFuel.GAS, 2010, 250_000, TypeCar.KOMBI, Color.NIEBIESKI);
        Car car2 = new Car(brandList.get(2), 1000, 1000,modelList.get(3), TypeFuel.DIESEL, 2020, 400_000, TypeCar.KABRIOLET, Color.POMARANCZOWY);
        Advertisement advertisement = new Advertisement(BigDecimal.valueOf(10000),  "titletitletitle", "content", cityList.get(0), car, seller , null );
        Advertisement advertisement1 = new Advertisement(BigDecimal.valueOf(1_324_300), "title23title23title23", "content321", cityList.get(3), car1, seller , null );
        Advertisement advertisement2 = new Advertisement(BigDecimal.valueOf(14_230),   "title1title1title1", "co213ntent312321", cityList.get(10), car2, seller , null );
        Advertisement advertisement6 = new Advertisement(BigDecimal.valueOf(9_484_121),"testytestytesty","testCont",cityList.get(5),car1,seller,null);
        seller.getAdvertisement().add(advertisement);
        seller.getAdvertisement().add(advertisement1);
        seller.getAdvertisement().add(advertisement2);
        seller.getAdvertisement().add(advertisement6);
        sellerService.save(seller);

        Seller seller1 = new Seller("123456", "123456789", TypeAccount.ADMIN, "111111111",  new HashSet<Advertisement>());
        Car car3 = new Car(brandList.get(10), 10, 100, modelList.get(4), TypeFuel.PETROL, 1990, 20_000, TypeCar.SEDAN, Color.ZOLTY);
        Advertisement advertisement3 = new Advertisement(BigDecimal.valueOf(100),   "123451234561234566", "kjhgfdsa", cityList.get(7), car3, seller, null);
        seller1.getAdvertisement().add(advertisement3);

        sellerService.save(seller1);

        Seller seller2 = new Seller("jdsnfsdn", "jaskdaslkfd", TypeAccount.MODERATOR, "123456789", new HashSet<Advertisement>());
        Car car4 = new Car((brandList.get(8)), 1000, 500, modelList.get(2), TypeFuel.GAS, 2005, 30_000, TypeCar.KABRIOLET, Color.BIALY);
        Advertisement advertisement4 = new Advertisement(BigDecimal.valueOf(100),   "title21321title21321title21321", "lalala", cityList.get(20),car4, seller2,null);
        seller2.getAdvertisement().add(advertisement4);

        sellerService.save(seller2);
    }
}
