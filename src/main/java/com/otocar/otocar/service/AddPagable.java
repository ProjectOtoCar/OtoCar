package com.otocar.otocar.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class AddPagable {
    protected Pageable pagable(int page) {
        if(page <= 0){
            page = 1;
        }
        return PageRequest.of((page - 1) , 10);
    }
}
