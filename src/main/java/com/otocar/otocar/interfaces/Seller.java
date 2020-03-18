package com.otocar.otocar.interfaces;

import com.otocar.otocar.enums.TypeAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface Seller<PAGE> {
    Page<com.otocar.otocar.model.Seller> findAllByOrderByCreateAccountAsc(PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByOrderByCreateAccountDesc(PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByFirstNameOrderByCreateAccountAsc(String firstName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByFirstNameOrderByCreateAccountDesc(String firstName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByLastNameOrderByCreateAccountAsc(String LastName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByLastNameOrderByCreateAccountDesc(String LastName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByFirstNameAndLastNameOrderByCreateAccountAsc(String firstName, String LastName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByFirstNameAndLastNameOrderByCreateAccountDesc(String firstName, String LastName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByTypeOrderByCreateAccountAsc(TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByTypeOrderByCreateAccountDesc(TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByFirstNameAndTypeOrderByCreateAccountAsc(String firstName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByFirstNameAndTypeOrderByCreateAccountDesc(String firstName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByLastNameAndTypeOrderByCreateAccountAsc(String LastName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByLastNameAndTypeOrderByCreateAccountDesc(String LastName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(String firstName, String LastName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(String firstName, String LastName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanOrderByCreateAccountAsc(LocalDate today, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanOrderByCreateAccountDesc(LocalDate today, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountDesc(LocalDate today, String firstName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountAsc(LocalDate today, String firstName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountAsc(LocalDate today, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountDesc(LocalDate today, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountAsc(LocalDate today, String lastName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountDesc(LocalDate today, String lastName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate today, String lastName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate today, String lastName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountAsc(LocalDate today, String firstName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountDesc(LocalDate today, String firstName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountAsc(LocalDate today, String firstName, String lastName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountDesc(LocalDate today, String firstName, String lastName, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate today, String firstName, String lastName, TypeAccount typeAccount, PAGE pageable);
    Page<com.otocar.otocar.model.Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate today, String firstName, String lastName, TypeAccount typeAccount, PAGE pageable);
}
