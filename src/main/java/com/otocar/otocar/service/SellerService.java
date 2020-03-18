package com.otocar.otocar.service;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SellerService extends AddPagable implements com.otocar.otocar.interfaces.Seller<Integer> {

    private SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    public Seller findById(Long aLong) {
        return sellerRepository.findById(aLong).orElse(null);
    }

    public Page<Seller> findAll(Integer page) {
        Pageable pageable = pagable(page);
        return sellerRepository.findAll(pageable);
    }



    public Iterable<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Seller save(Seller seller) {
        Optional<Seller> sellerOptional = Optional.of(sellerRepository.save(seller));
        return sellerOptional.orElse(null);
    }


    public Optional<Void> deleteById(Long along) {
        sellerRepository.deleteById(along);
        return Optional.empty();
    }

    public Seller change(Long aLong, Seller seller) {
        if(sellerRepository.findById(aLong).isEmpty()) {
            return sellerRepository.save(seller);
        }
        seller.setId(aLong);
        return sellerRepository.save(seller);
    }

    @Override
    public Page<Seller> findAllByOrderByCreateAccountAsc(Integer pageable) {
        return sellerRepository.findAllByOrderByCreateAccountAsc(pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByOrderByCreateAccountDesc(Integer pageable) {
        return sellerRepository.findAllByOrderByCreateAccountDesc(pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByFirstNameOrderByCreateAccountAsc(String firstName, Integer pageable) {
        return sellerRepository.findAllByFirstNameOrderByCreateAccountAsc(firstName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByFirstNameOrderByCreateAccountDesc(String firstName, Integer pageable) {
        return sellerRepository.findAllByFirstNameOrderByCreateAccountDesc(firstName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByLastNameOrderByCreateAccountAsc(String lastName, Integer pageable) {
        return sellerRepository.findAllByLastNameOrderByCreateAccountAsc(lastName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByLastNameOrderByCreateAccountDesc(String lastname, Integer pageable) {
        return sellerRepository.findAllByLastNameOrderByCreateAccountDesc(lastname, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByFirstNameAndLastNameOrderByCreateAccountAsc(String firstName, String lastName, Integer pageable) {
        return sellerRepository.findAllByFirstNameAndLastNameOrderByCreateAccountAsc(firstName,lastName,pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByFirstNameAndLastNameOrderByCreateAccountDesc(String firstName, String lastName, Integer pageable) {
        return sellerRepository.findAllByFirstNameAndLastNameOrderByCreateAccountDesc(firstName,lastName,pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByTypeOrderByCreateAccountAsc(TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByTypeOrderByCreateAccountAsc(typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByTypeOrderByCreateAccountDesc(TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByTypeOrderByCreateAccountDesc(typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByFirstNameAndTypeOrderByCreateAccountAsc(String firstName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByFirstNameAndTypeOrderByCreateAccountAsc(firstName,typeAccount,pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByFirstNameAndTypeOrderByCreateAccountDesc(String firstName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByFirstNameAndTypeOrderByCreateAccountDesc(firstName,typeAccount,pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByLastNameAndTypeOrderByCreateAccountAsc(String lastName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByLastNameAndTypeOrderByCreateAccountAsc(lastName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByLastNameAndTypeOrderByCreateAccountDesc(String lastName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByLastNameAndTypeOrderByCreateAccountDesc(lastName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(String firstName, String lastName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(firstName, lastName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(String firstName, String lastName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(firstName, lastName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanOrderByCreateAccountAsc(LocalDate today, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanOrderByCreateAccountAsc(today, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanOrderByCreateAccountDesc(LocalDate today, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanOrderByCreateAccountDesc(today, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountAsc(LocalDate today, String firstName, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountAsc(today, firstName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountAsc(LocalDate today, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountAsc(today, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountDesc(LocalDate today, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountDesc(today, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountDesc(LocalDate today, String firstName, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountDesc(today, firstName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountAsc(LocalDate today, String lastName, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountAsc(today, lastName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountDesc(LocalDate today, String lastName, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountDesc(today, lastName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate today, String lastName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountAsc(today, lastName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate today, String lastName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountDesc(today, lastName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountAsc(LocalDate today, String firstName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountAsc(today, firstName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountDesc(LocalDate today, String firstName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountDesc(today, firstName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountAsc(LocalDate today, String firstName, String lastName, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountAsc(today, firstName, lastName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountDesc(LocalDate today, String firstName, String lastName, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountDesc(today, firstName, lastName, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate today, String firstName, String lastName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(today, firstName, lastName, typeAccount, pagable(pageable));
    }

    @Override
    public Page<Seller> findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate today, String firstName, String lastName, TypeAccount typeAccount, Integer pageable) {
        return sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(today, firstName, lastName, typeAccount, pagable(pageable));
    }
}
