package com.otocar.gateway;

import com.otocar.gateway.model.AppUser;
import com.otocar.gateway.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Start {

//    private PasswordEncoder passwordEncoder;
//
//    private AppUserRepository appUserRepo;
//
//    @Autowired
//    public Start(PasswordEncoder passwordEncoder, AppUserRepository appUserRepo) {
//        this.passwordEncoder = passwordEncoder;
//        this.appUserRepo = appUserRepo;
//
//        AppUser appUser = new AppUser();
//        appUser.setUsername("Marian@o2");
//        appUser.setRole("ROLE_ADMIN");
//        appUser.setPassword(passwordEncoder.encode("Marian123!"));
//        appUser.setEnabled(true);
//
//        appUserRepo.save(appUser);
//    }
}
