package com.otocar.gateway.repository;

import com.otocar.gateway.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAllByUsername(String userName);
}
