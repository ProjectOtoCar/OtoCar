package com.otocar.otocar.repository;

import com.otocar.otocar.model.Image;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
}
