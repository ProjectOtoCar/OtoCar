package com.otocar.otocar.repository;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SellerRepository  extends PagingAndSortingRepository<Seller, Long>, com.otocar.otocar.interfaces.Seller<Pageable> {
    Page<Seller> findAllByOrderByCreateAccountAsc(Pageable pageable);
    Page<Seller> findAllByOrderByCreateAccountDesc(Pageable pageable);
    Page<Seller> findAllByFirstNameOrderByCreateAccountAsc(String firstName, Pageable pageable);
    Page<Seller> findAllByFirstNameOrderByCreateAccountDesc(String firstName, Pageable pageable);
    Page<Seller> findAllByLastNameOrderByCreateAccountAsc(String LastName, Pageable pageable);
    Page<Seller> findAllByLastNameOrderByCreateAccountDesc(String LastName, Pageable pageable);
    Page<Seller> findAllByFirstNameAndLastNameOrderByCreateAccountAsc(String firstName, String LastName, Pageable pageable);
    Page<Seller> findAllByFirstNameAndLastNameOrderByCreateAccountDesc(String firstName, String LastName, Pageable pageable);
    Page<Seller> findAllByTypeOrderByCreateAccountAsc(TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByTypeOrderByCreateAccountDesc(TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByFirstNameAndTypeOrderByCreateAccountAsc(String firstName, TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByFirstNameAndTypeOrderByCreateAccountDesc(String firstName, TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByLastNameAndTypeOrderByCreateAccountAsc(String LastName, TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByLastNameAndTypeOrderByCreateAccountDesc(String LastName, TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(String firstName, String LastName, TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(String firstName, String LastName, TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanOrderByCreateAccountAsc(LocalDate today, Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanOrderByCreateAccountDesc(LocalDate today, Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountDesc(LocalDate today, String firstName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountAsc(LocalDate today, String firstName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountAsc(LocalDate today, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountDesc(LocalDate today,TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountAsc(LocalDate today, String lastName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountDesc(LocalDate today, String lastName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate today,String lastName, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate today,String lastName, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountAsc(LocalDate today,String firstName, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountDesc(LocalDate today,String firstName, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountAsc(LocalDate today, String firstName,String lastName, Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountDesc(LocalDate today, String firstName,String lastName, Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate today, String firstName,String lastName,TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate today, String firstName,String lastName,TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanOrderByCreateAccountAsc(LocalDate today, Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanOrderByCreateAccountDesc(LocalDate today, Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndFirstNameOrderByCreateAccountDesc(LocalDate today, String firstName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndFirstNameOrderByCreateAccountAsc(LocalDate today, String firstName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndTypeOrderByCreateAccountAsc(LocalDate today, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndTypeOrderByCreateAccountDesc(LocalDate today,TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndLastNameOrderByCreateAccountAsc(LocalDate today, String lastName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndLastNameOrderByCreateAccountDesc(LocalDate today, String lastName,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate today,String lastName, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate today,String lastName, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndFirstNameAndTypeOrderByCreateAccountAsc(LocalDate today,String firstName, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndFirstNameAndTypeOrderByCreateAccountDesc(LocalDate today,String firstName, TypeAccount typeAccount,Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndFirstNameAndLastNameOrderByCreateAccountAsc(LocalDate today, String firstName,String lastName, Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndFirstNameAndLastNameOrderByCreateAccountDesc(LocalDate today, String firstName,String lastName, Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate today, String firstName,String lastName,TypeAccount typeAccount, Pageable pageable);
    Page<Seller> findAllByPremiumAccountLessThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate today, String firstName,String lastName,TypeAccount typeAccount, Pageable pageable);
    Seller findByAuthId(Long authId);
}
