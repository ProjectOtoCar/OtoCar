package com.otocar.otocar.service;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class SellerService extends AddPagable implements CrudServce<Long,Seller> {

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


    public Optional<Void> patch(Long aLong, Map<String, Object> fields) {
        boolean isEdit = false;
        Optional<Seller> optionalSeller = sellerRepository.findById(aLong);
        if(optionalSeller.isEmpty()) {
            return Optional.empty();
        }
        if(fields.size() == 0) {
            return Optional.empty();
        }
        if(fields.get("firstName") != null) {
            optionalSeller.get().setFirstName(((String)fields.get("firstName")));
            isEdit = true;
        }
        if(fields.get("lastName") != null) {
            optionalSeller.get().setLastName((String)fields.get("lastName"));
            isEdit = true;
        }
        if(fields.get("type") != null) {
            optionalSeller.get().setType(TypeAccount.valueOf((String)fields.get("type")));
            isEdit = true;
        }
        if(fields.get("phoneNumber") != null) {
            optionalSeller.get().setPhoneNumber(String.valueOf(fields.get("phoneNumber")));
            isEdit = true;
        }
        if(fields.get("premiumAccount") != null) {
            optionalSeller.get().setPremiumAccount(LocalDate.parse((String)fields.get("premiumAccount")));
            isEdit = true;
        }
        if(fields.get("lastAddvertisement") != null) {
            optionalSeller.get().setLastAddvertisement(LocalDate.parse((String)fields.get("lastAddvertisement")));
            isEdit = true;
        }
        if(isEdit) {
            sellerRepository.save(optionalSeller.get());
        }
        return Optional.empty();
    }
    private Page<Seller> findALlByNameAsc(String firstName, String lastName, int page) {
        Page<Seller> sellerPage = null;

        if(firstName != null && lastName != null) {
            sellerPage = sellerRepository.findAllByFirstNameAndLastNameOrderByCreateAccountAsc(firstName, lastName, pagable(page));
        } else if (firstName != null) {
            sellerPage = sellerRepository.findAllByFirstNameOrderByCreateAccountAsc(firstName, pagable(page));
        } else if(lastName !=null) {
            sellerPage = sellerRepository.findAllByLastNameOrderByCreateAccountAsc(lastName, pagable(page));
        } else {
            sellerPage = sellerRepository.findAllByOrderByCreateAccountAsc(pagable(page));
        }
        return sellerPage;
    }
    private Page<Seller> findALlByNameDesc(String firstName, String lastName, int page) {
        Page<Seller> sellerPage = null;
        if(firstName != null && lastName != null) {
            sellerPage = sellerRepository.findAllByFirstNameAndLastNameOrderByCreateAccountDesc(firstName, lastName, pagable(page));
        } else if (firstName != null) {
            sellerPage = sellerRepository.findAllByFirstNameOrderByCreateAccountDesc(firstName, pagable(page));
        } else if(lastName !=null) {
            sellerPage = sellerRepository.findAllByLastNameOrderByCreateAccountDesc(lastName, pagable(page));
        } else {
            sellerPage = sellerRepository.findAllByOrderByCreateAccountDesc(pagable(page));
        }
        return sellerPage;
    }
    private Page<Seller> findAllByName(String firstName, String lastName, boolean sort, int page) {
        Page<Seller> sellerPage = null;
        if(sort) {
            sellerPage = findALlByNameDesc(firstName, lastName, page);
        } else {
            sellerPage = findALlByNameAsc(firstName, lastName, page);
        }
        return sellerPage;
    }

    private Page<Seller> findAllByNameAndType(String firstName, String lastName, TypeAccount typeAccount, boolean sort, int page) {
        Page<Seller> sellerPage = null;
        if(typeAccount == null) {
            sellerPage = findAllByName(firstName, lastName, sort, page);
        } else {
            if(sort) {
                sellerPage = findAllByNameAndTypeDesc(firstName, lastName, typeAccount, page);
            } else {
                sellerPage = findAllByNameAndTypeAsc(firstName,lastName,typeAccount,page);
            }
        }
        return sellerPage;
    }

    private Page<Seller> findAllByNameAndTypeDesc(String firstName, String lastName, TypeAccount typeAccount, int page) {
        Page<Seller> sellerPage = null;
        if(firstName != null && lastName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(firstName, lastName,typeAccount, pagable(page));
        } else if (firstName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByFirstNameAndTypeOrderByCreateAccountDesc(firstName, typeAccount, pagable(page));
        } else if(lastName !=null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByLastNameAndTypeOrderByCreateAccountDesc(lastName, typeAccount, pagable(page));
        } else if(typeAccount !=null) {
            sellerPage = sellerRepository.findAllByTypeOrderByCreateAccountDesc(typeAccount, pagable(page));
        } else {
            sellerPage = sellerRepository.findAllByOrderByCreateAccountDesc(pagable(page));
        }
        return sellerPage;
    }

    private Page<Seller> findAllByNameAndTypeAsc(String firstName, String lastName, TypeAccount typeAccount, int page) {
        Page<Seller> sellerPage = null;

        if(firstName != null && lastName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(firstName, lastName,typeAccount, pagable(page));
        } else if (firstName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByFirstNameAndTypeOrderByCreateAccountAsc(firstName, typeAccount, pagable(page));
        } else if(lastName !=null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByLastNameAndTypeOrderByCreateAccountAsc(lastName, typeAccount, pagable(page));
        } else if(typeAccount !=null) {
            sellerPage = sellerRepository.findAllByTypeOrderByCreateAccountAsc(typeAccount, pagable(page));
        } else {
            sellerPage = sellerRepository.findAllByOrderByCreateAccountAsc(pagable(page));
        }
        return sellerPage;
    }
    private Page<Seller> findAllByNameAndTypeIsPremiumAsc(String firstName, String lastName, TypeAccount typeAccount,int page) {
        Page<Seller> sellerPage = null;
        if(firstName != null && lastName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate.now(),firstName, lastName, typeAccount, pagable(page));
        } else if (firstName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountAsc(LocalDate.now(),firstName, typeAccount, pagable(page));
        } else if(lastName !=null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate.now(), lastName, typeAccount, pagable(page));
        } else if(firstName != null && lastName != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountAsc(LocalDate.now(), firstName, lastName, pagable(page));
        } else if(firstName != null ) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountAsc(LocalDate.now(), firstName, pagable(page));
        } else if(lastName != null ) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountAsc(LocalDate.now(), lastName, pagable(page));
        } else if(typeAccount !=null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountAsc(LocalDate.now(),typeAccount, pagable(page));
        } else {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanOrderByCreateAccountAsc(LocalDate.now(), pagable(page));
        }
        return sellerPage;
    }
    private Page<Seller> findAllByNameAndTypeIsPremiumDesc(String firstName, String lastName, TypeAccount typeAccount,int page) {
        Page<Seller> sellerPage = null;
        if(firstName != null && lastName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate.now(),firstName, lastName, typeAccount, pagable(page));
        } else if (firstName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountDesc(LocalDate.now(),firstName, typeAccount, pagable(page));
        } else if(lastName !=null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate.now(), lastName, typeAccount, pagable(page));
        } else if(firstName != null && lastName != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountDesc(LocalDate.now(), firstName, lastName, pagable(page));
        } else if(firstName != null ) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountDesc(LocalDate.now(), firstName, pagable(page));
        } else if(lastName != null ) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountDesc(LocalDate.now(), lastName, pagable(page));
        }else if(typeAccount !=null) {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountDesc(LocalDate.now(),typeAccount, pagable(page));
        } else {
            sellerPage = sellerRepository.findAllByPremiumAccountGreaterThanOrderByCreateAccountDesc(LocalDate.now(), pagable(page));
        }
        return sellerPage;
    }
    private Page<Seller> findAllByNameAndTypeIsNotPremiumDesc(String firstName, String lastName, TypeAccount typeAccount,int page) {
        Page<Seller> sellerPage = null;
        if(firstName != null && lastName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate.now(),firstName, lastName, typeAccount, pagable(page));
        } else if (firstName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndTypeOrderByCreateAccountDesc(LocalDate.now(),firstName, typeAccount, pagable(page));
        } else if(lastName !=null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndLastNameAndTypeOrderByCreateAccountDesc(LocalDate.now(), lastName, typeAccount, pagable(page));
        } else if(firstName != null && lastName != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndLastNameOrderByCreateAccountDesc(LocalDate.now(), firstName, lastName, pagable(page));
        } else if(firstName != null ) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndFirstNameOrderByCreateAccountDesc(LocalDate.now(), firstName, pagable(page));
        } else if(lastName != null ) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndLastNameOrderByCreateAccountDesc(LocalDate.now(), lastName, pagable(page));
        }else if(typeAccount !=null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndTypeOrderByCreateAccountDesc(LocalDate.now(),typeAccount, pagable(page));
        } else {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanOrderByCreateAccountDesc(LocalDate.now(), pagable(page));
        }
        return sellerPage;
    }
    private Page<Seller> findAllByNameAndTypeIsNotPremiumAsc(String firstName, String lastName, TypeAccount typeAccount,int page) {
        Page<Seller> sellerPage = null;
        if(firstName != null && lastName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate.now(),firstName, lastName, typeAccount, pagable(page));
        } else if (firstName != null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndTypeOrderByCreateAccountAsc(LocalDate.now(),firstName, typeAccount, pagable(page));
        } else if(lastName !=null && typeAccount != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndLastNameAndTypeOrderByCreateAccountAsc(LocalDate.now(), lastName, typeAccount, pagable(page));
        } else if(firstName != null && lastName != null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndLastNameOrderByCreateAccountAsc(LocalDate.now(), firstName, lastName, pagable(page));
        } else if(firstName != null ) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndFirstNameOrderByCreateAccountAsc(LocalDate.now(), firstName, pagable(page));
        } else if(lastName != null ) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndLastNameOrderByCreateAccountAsc(LocalDate.now(), lastName, pagable(page));
        } else if(typeAccount !=null) {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanAndTypeOrderByCreateAccountAsc(LocalDate.now(),typeAccount, pagable(page));
        } else {
            sellerPage = sellerRepository.findAllByPremiumAccountLessThanOrderByCreateAccountAsc(LocalDate.now(), pagable(page));
        }
        return sellerPage;
    }


    public Page<Seller> findAllByNameAndTypeAndPremium(String firstName, String lastName, String type, String isSort, String premium, int page) {
        boolean sort = false;
        TypeAccount typeAccount = null;
        if(isSort.length() > 0){
            sort = true;
        }
        if(type != null) {
            try {
                typeAccount = TypeAccount.valueOf(type);
            } catch (Exception e) {
                typeAccount = null;
            }
        }
        Page<Seller> sellerPage = null;
        if(premium == null) {
            sellerPage = findAllByNameAndType(firstName, lastName, typeAccount, sort, page);
        }
        else if(premium.toLowerCase().equals("true")) {
            if(sort) {
                sellerPage = findAllByNameAndTypeIsPremiumDesc(firstName, lastName, typeAccount, page);
            } else {
                sellerPage = findAllByNameAndTypeIsPremiumAsc(firstName,lastName,typeAccount,page);
            }
        } else {
            if(sort) {
                sellerPage = findAllByNameAndTypeIsNotPremiumDesc(firstName, lastName, typeAccount, page);
            } else {
                sellerPage = findAllByNameAndTypeIsNotPremiumAsc(firstName, lastName, typeAccount, page);
            }
        }

        return sellerPage;
    }

}
