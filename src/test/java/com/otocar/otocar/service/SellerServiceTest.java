package com.otocar.otocar.service;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.SellerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @Mock
    SellerRepository sellerRepository;

    @InjectMocks
    SellerService sellerService;


    final String firstName = "test";
    final Long id = 1L;


    @Test
    void findById() {
        final String firstName = "test";
        final Long id = 1L;
        Seller seller = new Seller();
        seller.setId(id);
        seller.setFirstName(firstName);
        given(sellerRepository.findById(id)).willReturn(Optional.of(seller));

        Seller foundSeller = sellerService.findById(id);

        then(sellerRepository).should().findById(id);
        then(sellerRepository).shouldHaveNoMoreInteractions();
        assertThat(foundSeller).isNotNull();
        assertThat(foundSeller.getFirstName()).isEqualTo(firstName);
        assertThat(foundSeller.getId()).isEqualTo(id);
    }
    @Test
    void findByIdNotFound() {

        Seller seller = new Seller();
        seller.setId(id);
        seller.setFirstName(firstName);
        given(sellerRepository.findById(anyLong())).willReturn(Optional.empty());

        Seller foundSeller = sellerService.findById(id);

        then(sellerRepository).should().findById(id);
        then(sellerRepository).shouldHaveNoMoreInteractions();
        assertThat(foundSeller).isNull();
    }

    @Test
    void save() {
        Seller seller = new Seller();
        seller.setFirstName(firstName);

        given(sellerRepository.save(any(Seller.class))).willReturn(seller);

        Seller foundSeller = sellerService.save(seller);

        then(sellerRepository).should().save(any(Seller.class));
        then(sellerRepository).shouldHaveNoMoreInteractions();
        assertThat(foundSeller).isNotNull();
        assertThat(foundSeller.getFirstName()).isEqualTo(firstName);
    }

    @Test
    void findAllWithoutPagable() {
        final String firstName2 = "test2";
        List<Seller> sellerSet = new ArrayList<Seller>();
        Seller seller = new Seller();
        seller.setFirstName(firstName);
        Seller seller1 = new Seller();
        seller1.setFirstName(firstName2);
        sellerSet.add(seller);
        sellerSet.add(seller1);

        given(sellerRepository.findAll()).willReturn(sellerSet);

        Iterable<Seller> foundSellerSet = sellerService.findAll();

        assertThat(foundSellerSet).isNotNull();

        List<Seller> foundSellers = new ArrayList<>();

        foundSellerSet.forEach(foundSellers::add);

        then(sellerRepository).should().findAll();
        then(sellerRepository).shouldHaveNoMoreInteractions();

        assertThat(foundSellers.size()).isEqualTo(sellerSet.size());

        assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
        assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName2);

    }

    @Test
    void findAllWithPagable() {
        final String firstName2 = "test2";
        ArrayList<Seller> sellerArrayList = new ArrayList<>();
        Seller seller = new Seller();
        seller.setFirstName(firstName);
        Seller seller1 = new Seller();
        seller1.setFirstName(firstName2);
        sellerArrayList.add(seller);
        sellerArrayList.add(seller1);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

        given(sellerRepository.findAll(pageable)).willReturn(sellerPage);

        Iterable<Seller> foundSellerSet = sellerService.findAll(1);

        assertThat(foundSellerSet).isNotNull();

        List<Seller> foundSellers = new ArrayList<>();

        foundSellerSet.forEach(foundSellers::add);

        then(sellerRepository).should().findAll(pageable);
        then(sellerRepository).shouldHaveNoMoreInteractions();

        assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

        assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
        assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName2);
    }


    @Test
    void deleteById() {
        Seller seller = new Seller();
        seller.setId(id);
        sellerService.deleteById(id);

        then(sellerRepository).should().deleteById(id);
        then(sellerRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void changeObjectExist() {
        Seller seller = new Seller();
        Seller changeSeller = new Seller();
        seller.setId(id);
        changeSeller.setFirstName(firstName);
        given(sellerRepository.findById(id)).willReturn(Optional.of(seller));
        given(sellerRepository.save(changeSeller)).willReturn(changeSeller);

        Seller foundSeller = sellerService.change(id, changeSeller);

        then(sellerRepository).should().findById(anyLong());
        then(sellerRepository).should().save(any(Seller.class));
        then(sellerRepository).shouldHaveNoMoreInteractions();

        assertThat(foundSeller).isNotNull();
        assertThat(foundSeller.getFirstName()).isEqualTo(firstName);
        assertThat(foundSeller.getId()).isEqualTo(id);
    }

    @Test
    void changeObjectNotExist() {
        final Long newId = 2L;
        Seller seller = new Seller();
        Seller changeSeller = new Seller();
        seller.setId(id);
        changeSeller.setFirstName(firstName);
        changeSeller.setId(2L);
        given(sellerRepository.findById(anyLong())).willReturn(Optional.empty());
        given(sellerRepository.save(changeSeller)).willReturn(changeSeller);

        Seller foundSeller = sellerService.change(newId, changeSeller);

        then(sellerRepository).should().findById(anyLong());
        then(sellerRepository).should().save(any(Seller.class));
        then(sellerRepository).shouldHaveNoMoreInteractions();

        assertThat(foundSeller).isNotNull();
        assertThat(foundSeller.getFirstName()).isEqualTo(firstName);
        assertThat(foundSeller.getId()).isEqualTo(newId);
    }

    @Test
    void patchNotChange() {
        Map<String, Object> fields = new HashMap<>();
       Seller seller = new Seller();
       seller.setId(id);
       seller.setFirstName(firstName);
       given(sellerRepository.findById(anyLong())).willReturn(Optional.of(seller));
       sellerService.patch(id,fields);

        then(sellerRepository).should().findById(anyLong());
        then(sellerRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(0);
    }

    static Stream<Arguments> getArgsToPatchChange() {
        String firstName ="Kingg";
        String lastName = "hakaw";
        TypeAccount typeAccount = TypeAccount.MODERATOR;
        String phoneNumber = "345678901";
        Long id = 5L;
        LocalDate lastAdd = LocalDate.now().minusWeeks(1);
        LocalDate premium = LocalDate.now().plusMonths(3);
        Seller seller = new Seller(firstName,lastName,typeAccount,phoneNumber,null);
        seller.setLastAddvertisement(lastAdd);
        seller.setId(id);
        return Stream.of(Arguments.of(seller,"firstName",firstName,id),
                Arguments.of(seller,"lastName",lastName,id),
                Arguments.of(seller,"type",typeAccount.name(),id),
                Arguments.of(seller,"phoneNumber",phoneNumber,id),
                Arguments.of(seller,"premiumAccount",premium.toString(),id),
                Arguments.of(seller,"lastAddvertisement",lastAdd.toString(),id)
                );
    }
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgsToPatchChange")
    void patchChange(Seller seller, String key, Object value,Long id) {
        Map<String, Object> fields = new HashMap<>();
        fields.put(key,value);
        given(sellerRepository.findById(anyLong())).willReturn(Optional.of(seller));
        given(sellerRepository.save(any(Seller.class))).willReturn(seller);
        sellerService.patch(id,fields);

        then(sellerRepository).should().findById(anyLong());
        then(sellerRepository).should().save(any(Seller.class));
        then(sellerRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }
    @Test
    void patchWithWrongKey() {
        final String firstName2 = "test2";
        Map<String, Object> fields = new HashMap<>();
        Seller seller = new Seller();
        seller.setId(id);
        seller.setFirstName(firstName);
        fields.put("firstName1", firstName2);
        Seller saveSeller = new Seller();
        saveSeller.setId(id);
        saveSeller.setFirstName(firstName2);
        given(sellerRepository.findById(anyLong())).willReturn(Optional.of(seller));
        sellerService.patch(id,fields);

        then(sellerRepository).should().findById(anyLong());
        then(sellerRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }

    @Nested
    class FilterSeller {

        ArrayList<Seller> sellerArrayList;
        Pageable pageable;

        final String lastName = "last";
        final String desc = "desc";
        final String asc = "";
        final String isPremium = "true";
        final String isNotPremium = "false";
        final String type = TypeAccount.NORMAL.name();
        final LocalDate today = LocalDate.now();
        final Seller seller = new Seller(firstName,lastName,TypeAccount.valueOf(type),null,null);
        final Seller seller1 = new Seller(firstName,lastName,TypeAccount.valueOf(type),null,null);

        @BeforeEach
        public void setUp() {
            pageable = PageRequest.of(0, 10);
            sellerArrayList = new ArrayList<>();
            sellerArrayList.add(seller);
            sellerArrayList.add(seller1);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByOrderByCreateAccountDesc(pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,null,desc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByOrderByCreateAccountDesc(pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByOrderByCreateAccountAsc(pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,null,asc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByOrderByCreateAccountAsc(pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByFirstNameOrderByCreateAccountDesc(firstName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,null,desc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByFirstNameOrderByCreateAccountDesc(firstName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByFirstNameOrderByCreateAccountAsc(firstName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,null,asc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByFirstNameOrderByCreateAccountAsc(firstName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByLastNameOrderByCreateAccountDesc(lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,null,desc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByLastNameOrderByCreateAccountDesc(lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByLastNameOrderByCreateAccountAsc(lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,null,asc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByLastNameOrderByCreateAccountAsc(lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByFirstNameAndLastNameOrderByCreateAccountAsc(firstName,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,null,asc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByFirstNameAndLastNameOrderByCreateAccountAsc(firstName,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByFirstNameAndLastNameOrderByCreateAccountDesc(firstName,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,null,desc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByFirstNameAndLastNameOrderByCreateAccountDesc(firstName,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyTypeDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByTypeOrderByCreateAccountDesc(TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,type,desc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByTypeOrderByCreateAccountDesc(TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyTypeAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByTypeOrderByCreateAccountAsc(TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,type,asc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByTypeOrderByCreateAccountAsc(TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndTypeNameDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByFirstNameAndTypeOrderByCreateAccountDesc(firstName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,type,desc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByFirstNameAndTypeOrderByCreateAccountDesc(firstName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndTypeNameAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByFirstNameAndTypeOrderByCreateAccountAsc(firstName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,type,asc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByFirstNameAndTypeOrderByCreateAccountAsc(firstName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndTypeNameDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByLastNameAndTypeOrderByCreateAccountDesc(lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,type,desc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByLastNameAndTypeOrderByCreateAccountDesc(lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndTypeNameAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByLastNameAndTypeOrderByCreateAccountAsc(lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,type,asc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByLastNameAndTypeOrderByCreateAccountAsc(lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastAndTypeNameDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(firstName,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,type,desc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(firstName,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastAndTypeNameAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(firstName,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,type,asc,null,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(firstName,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyIsPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanOrderByCreateAccountDesc(today,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,null,desc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanOrderByCreateAccountDesc(today,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());
            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyIsPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanOrderByCreateAccountAsc(today,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,null,asc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanOrderByCreateAccountAsc(today,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndIsPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountAsc(today,firstName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,null,asc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountAsc(today,firstName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndIsPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountDesc(today,firstName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,null,desc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndFirstNameOrderByCreateAccountDesc(today,firstName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyTypeAndIsPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountDesc(today,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,type,desc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountDesc(today,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyTypeAndIsPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountAsc(today,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,type,asc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndTypeOrderByCreateAccountAsc(today,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndIsPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountDesc(today,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,null,desc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountDesc(today,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndIsPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountAsc(today,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,null,asc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndLastNameOrderByCreateAccountAsc(today,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndTypeAndIsPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountDesc(today,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,type,desc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountDesc(today,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndTypeAndIsPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountAsc(today,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,type,asc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndLastNameAndTypeOrderByCreateAccountAsc(today,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndTypeAndIsPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountDesc(today,firstName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,type,desc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountDesc(today,firstName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndTypeAndIsPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountAsc(today,firstName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,type,asc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndFirstNameAndTypeOrderByCreateAccountAsc(today,firstName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAndIsPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountDesc(today,firstName,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,null,desc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountDesc(today,firstName,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAndIsPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountAsc(today,firstName,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,null,asc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameOrderByCreateAccountAsc(today,firstName,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAndTypeAndIsPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(today,firstName,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,type,desc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(today,firstName,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAndTypeAndIsPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(today,firstName,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,type,asc,isPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountGreaterThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(today,firstName,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyIsNotPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanOrderByCreateAccountDesc(today,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,null,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanOrderByCreateAccountDesc(today,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());
            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyIsNotPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanOrderByCreateAccountAsc(today,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,null,asc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanOrderByCreateAccountAsc(today,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndIsNotPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndFirstNameOrderByCreateAccountAsc(today,firstName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,null,asc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndFirstNameOrderByCreateAccountAsc(today,firstName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndIsNotPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndFirstNameOrderByCreateAccountDesc(today,firstName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,null,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndFirstNameOrderByCreateAccountDesc(today,firstName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyTypeAndIsNotPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndTypeOrderByCreateAccountDesc(today,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,type,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndTypeOrderByCreateAccountDesc(today,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyTypeAndIsNotPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndTypeOrderByCreateAccountAsc(today,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,null,type,asc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndTypeOrderByCreateAccountAsc(today,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndIsNotPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndLastNameOrderByCreateAccountDesc(today,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,null,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndLastNameOrderByCreateAccountDesc(today,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndIsNotPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndLastNameOrderByCreateAccountDesc(today,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,null,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndLastNameOrderByCreateAccountDesc(today,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndTypeAndIsNotPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndLastNameAndTypeOrderByCreateAccountDesc(today,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,type,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndLastNameAndTypeOrderByCreateAccountDesc(today,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyLastNameAndTypeAndIsNotPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndLastNameAndTypeOrderByCreateAccountAsc(today,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(null,lastName,type,asc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndLastNameAndTypeOrderByCreateAccountAsc(today,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndTypeAndIsNotPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndTypeOrderByCreateAccountDesc(today,firstName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,type,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndFirstNameAndTypeOrderByCreateAccountDesc(today,firstName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndTypeAndIsNotPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndTypeOrderByCreateAccountAsc(today,firstName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,null,type,asc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndFirstNameAndTypeOrderByCreateAccountAsc(today,firstName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAndIsNotPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndLastNameOrderByCreateAccountDesc(today,firstName,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,null,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndFirstNameAndLastNameOrderByCreateAccountDesc(today,firstName,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAndIsNotPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndLastNameOrderByCreateAccountAsc(today,firstName,lastName,pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,null,asc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndFirstNameAndLastNameOrderByCreateAccountAsc(today,firstName,lastName,pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAndTypeAndIsNotPremiumDesc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(today,firstName,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,type,desc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountDesc(today,firstName,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }
        @Test
        void findAllByNameAndTypeAndPremium_OnlyFirstNameAndLastNameAndTypeAndIsNotPremiumAsc() {
            Page<Seller> sellerPage = new PageImpl<>(sellerArrayList,pageable,sellerArrayList.size());

            given(sellerRepository.findAllByPremiumAccountLessThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(today,firstName,lastName,TypeAccount.valueOf(type),pageable)).willReturn(sellerPage);

            Iterable<Seller> foundSellerSet = sellerService.findAllByNameAndTypeAndPremium(firstName,lastName,type,asc,isNotPremium,1);

            assertThat(foundSellerSet).isNotNull();

            List<Seller> foundSellers = new ArrayList<>();

            foundSellerSet.forEach(foundSellers::add);

            then(sellerRepository).should().findAllByPremiumAccountLessThanAndFirstNameAndLastNameAndTypeOrderByCreateAccountAsc(today,firstName,lastName,TypeAccount.valueOf(type),pageable);
            then(sellerRepository).shouldHaveNoMoreInteractions();

            assertThat(foundSellers.size()).isEqualTo(sellerPage.getTotalElements());

            assertThat(foundSellers.get(0).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(1).getLastName()).isEqualTo(lastName);
            assertThat(foundSellers.get(0).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(1).getFirstName()).isEqualTo(firstName);
            assertThat(foundSellers.get(0).getType()).isEqualTo(TypeAccount.valueOf(type));
            assertThat(foundSellers.get(1).getType()).isEqualTo(TypeAccount.valueOf(type));
        }

    }
}