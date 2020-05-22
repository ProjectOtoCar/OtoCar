package com.otocar.gateway.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.otocar.gateway.model.AppUser;
import com.otocar.gateway.repository.AppUserRepository;
import com.otocar.gateway.service.UserDetailsServiceImpl;
import com.otocar.gateway.service.UserSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final String KEY_ = "McQfTjWnZr4u7x!A%D*G-KaNdRgUkXp2s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F";
    private UserDetailsServiceImpl appUserRepository;
    private PasswordEncoder passwordEncoder;
    private AppUserRepository userRepository;
    private UserSevice userSevice;

    public UserController(UserDetailsServiceImpl appUserRepository, PasswordEncoder passwordEncoder, AppUserRepository userRepository, UserSevice userSevice) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userSevice = userSevice;
    }

    @PostMapping("/regi")
    ResponseEntity<AppUser> addAcc(@RequestBody AppUser user, HttpServletRequest request) {
        if (userSevice.isExistAccount(user.getUsername())) {
            AppUser appUser = userSevice.addNeUser(user, request);
            return ResponseEntity.created(URI.create("localhost:8080/user/" + appUser.getId())).body(appUser);
        }
        return ResponseEntity.badRequest().body(user);
    }

    @GetMapping("/email/{id}")
    ResponseEntity<Map<String,String>> getEmailById(@PathVariable Long id){
        Optional<AppUser> user = userRepository.findById(id);
        return ResponseEntity.ok(Map.of("email", user.get().getUsername()));
    }

    @GetMapping("/role/{id}")
    ResponseEntity<Map<String,String>> getRoleById(@PathVariable Long id){
        Optional<AppUser> user = userRepository.findById(id);
        return ResponseEntity.ok(Map.of("role", user.get().getRole()));
    }
    @PostMapping()
    ResponseEntity<Map<String,String>> login(@RequestBody AppUser user) {
        String sign = null;
        UserDetails userDetails = appUserRepository.loadUserByUsername(user.getUsername());
        if (userDetails != null && BCrypt.checkpw(user.getPassword(), userDetails.getPassword())) {
            sign = JWT.create().withClaim("name",userDetails.getUsername()).withClaim("role", "ROLE_ADMIN").sign(Algorithm.HMAC512(KEY_));

        }
        AppUser allByUsername = userRepository.findAllByUsername(user.getUsername());
        System.out.println(allByUsername.getId());

        return ResponseEntity.ok(Map.of("key",allByUsername.getId()+" "+sign));
    }

    @GetMapping("/verifyToken")
    public String verifyToken(@RequestParam String token) {
        userSevice.verificationToken(token);
        return "Zrobione";
    }

    @PostMapping("/reset")
    ResponseEntity<Boolean> resetPassword(@RequestParam String username, HttpServletRequest request) {
        //  boolean b = token.endsWith("=");
        //  System.out.println(b);
        UserDetails user = appUserRepository.loadUserByUsername(username);
        if (user != null) {
            userSevice.resetPassword(username, request);
        }
        return ResponseEntity.ok(true);
    }

    // Do zrobienia weryfikacja hasla i zmiena hasła
    @GetMapping("/reset")
    ResponseEntity<String> verifyTokenAndSetNewPassword(@RequestParam String token) {
        int i = token.indexOf("_");
        String id = token.substring(0, i);
        System.out.println();
        userSevice.verificationToken(token);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/changePassword")
    void changePassword(@RequestBody AppUser user) {
        AppUser userDetails = userRepository.findAllByUsername(user.getUsername());
        userDetails.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userDetails);
    }


}
