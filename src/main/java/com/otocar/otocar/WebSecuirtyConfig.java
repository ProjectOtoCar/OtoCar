package com.otocar.otocar;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecuirtyConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/seller", "/api/mail").permitAll()
            .antMatchers(HttpMethod.GET, "/api/brand","/api/car","/api/carModel","/api/advertisement/**","/api/city","/api/enums").permitAll()
            .antMatchers(HttpMethod.DELETE,"/api/seller","/api/advertisement/**").authenticated()
            .antMatchers(HttpMethod.PUT,"/api/seller","/api/advertisement/**").authenticated()
            .antMatchers(HttpMethod.POST, "/api/advertisement/**").authenticated()
            .antMatchers(HttpMethod.PATCH,"/api/seller","/api/advertisement/**").authenticated();
        http.csrf().disable();
    }
}
