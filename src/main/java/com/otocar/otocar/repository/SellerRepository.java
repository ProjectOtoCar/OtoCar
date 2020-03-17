package com.otocar.otocar.repository;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SellerRepository  extends PagingAndSortingRepository<Seller, Long> {
    Page<Seller> findAllByLastName(String lastName, Pageable pageable);
    Page<Seller> findAllByFirstName(String firstName, Pageable pageable);
    Page<Seller> findAllByFirstNameAndLastName(String firstName, String LastName, Pageable pageable);
    Page<Seller> findAllByType(TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByFirstNameAndType(String firstName, TypeAccount typeAccount ,Pageable pageable);
    Page<Seller> findAllByLastNameAndType(String firstName, TypeAccount typeAccount ,Pageable pageable);
    Page<Seller> findAllByFirstNameAndLastNameAndType(String firstName, String  lastName, TypeAccount typeAccount ,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThan(LocalDate today, Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstName(LocalDate today, String firstName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndLastName(LocalDate today, String lastName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastName(LocalDate today, String firstName,String lastName, Pageable pageable);
}
