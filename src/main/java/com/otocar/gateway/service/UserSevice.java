package com.otocar.gateway.service;

import com.otocar.gateway.model.AppUser;
import com.otocar.gateway.model.VerificationToken;
import com.otocar.gateway.repository.AppUserRepository;
import com.otocar.gateway.repository.VerificationTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Service
public class UserSevice {
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    private MailSenderService mailSenderService;
    private VerificationTokenRepository verificationTokenRepository;

    public UserSevice(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, MailSenderService mailSenderService, VerificationTokenRepository verificationTokenRepository) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public AppUser addNeUser(AppUser user, HttpServletRequest request) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser appUser = appUserRepository.save(user);

        String url = createToken(user, request, "verifyToken");


        try {
            mailSenderService.sendMail(user.getUsername(), "Verification Token", url);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return appUser;
    }

    public void verificationToken(String verifyToken) {
        AppUser appUser = verificationTokenRepository.findByValue(verifyToken).getAppUser();
        appUser.setEnabled(true);
        appUserRepository.save(appUser);
    }

    String createToken(AppUser user, HttpServletRequest request, String type) {
        String verifyToken =user.getId()+"_" + UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(verifyToken, user);
        verificationTokenRepository.save(verificationToken);
        String url = "<html><head></head><body><a href =\"http://" + request.getServerName() +
                ":" +
                request.getServerPort() + "/" +
                request.getContextPath() +
                "test/" + type + "?token="+ verifyToken + "\"> Aktywuj Konto </a></body></html>";
        return url;
    }

   public void resetPassword(String userName, HttpServletRequest request) {
        AppUser user = appUserRepository.findAllByUsername(userName);
        if (user != null) {
            String token = createToken(user, request, "reset");
            try {
                mailSenderService.sendMail(userName, "Reset your password", token);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }


}
