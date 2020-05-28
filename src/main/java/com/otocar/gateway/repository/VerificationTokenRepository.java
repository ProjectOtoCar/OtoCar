package com.otocar.gateway.repository;

import com.otocar.gateway.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByValue(String value);
    VerificationToken findByAppUser_Id(Long id);
}
