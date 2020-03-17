package com.otocar.otocar.bootstrap;

import com.otocar.otocar.model.Brand;
import com.otocar.otocar.model.Car;
import com.otocar.otocar.model.CarModel;
import com.otocar.otocar.service.BrandService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;

@Component
public class BrandLoader implements CommandLineRunner {

    private final BrandService brandService;

    public BrandLoader(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(((Collection<Brand>)brandService.findAll()).size() == 0) {
            addedBrand();
        }

    }

    private void addedBrand() throws IOException {

        InputStream inputStream = ClassLoader.getSystemResourceAsStream("brands_and_models.csv");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null;) {
            boolean isFirst = true;
            String name = "";
            Brand brand = new Brand();
            brand.setCarModels(new HashSet<CarModel>());
            for (int i = 0; i < line.length(); i++) {
                if(line.charAt(i) != ',') {
                    name += line.charAt(i);
                } else {
                    if(isFirst && name.length() > 0) {
                            name = name.trim();
                            brand.setName(name);
                            isFirst = false;
                        } else if(name.length() > 0) {
                            CarModel carModel = new CarModel(name,brand, new HashSet<Car>());
                            brand.getCarModels().add(carModel);
                        }
                    name = "";
                    }
                }
            if(isFirst == false) {
                brandService.save(brand);
            }
            }
        }

    }

