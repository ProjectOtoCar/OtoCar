package com.otocar.otocar.bootstrap;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.model.City;
import com.otocar.otocar.service.CityService;
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
public class CityLoader implements CommandLineRunner {

    private final CityService cityService;

    public CityLoader(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(((Collection<City>)cityService.findAll()).size() == 0) {
            addCity();
        }
    }
    private void addCity() throws IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("cities.csv");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null;) {
            City city = new City(line.trim(),new HashSet<Advertisement>());
            cityService.save(city);
        }
    }
}
