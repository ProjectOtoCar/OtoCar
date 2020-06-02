package com.otocar.otocar;

import com.otocar.otocar.model.Advertisement;
import com.otocar.otocar.repository.AdvertisementRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@Configuration
public class ScheduleConfig {
    private AdvertisementRepository repository;

    public ScheduleConfig(AdvertisementRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 6 * * ?")
    public void isExist() {
        Iterable<Advertisement> all = repository.findAll();
        List<Advertisement> advertisements = new ArrayList<>();
        all.forEach(advertisement -> advertisements.add(advertisement));

        advertisements.stream()
            .filter(a -> a.getDateAdd().plusDays(30).isBefore(LocalDate.now()))
            .forEach(advertisement -> {
                advertisement.setActive(false);
                repository.save(advertisement);
            });
    }
}
