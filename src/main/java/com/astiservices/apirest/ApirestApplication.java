package com.astiservices.apirest;

import com.astiservices.apirest.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class ApirestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApirestApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login", "/signup").permitAll()
                    //.antMatchers(HttpMethod.GET, "/employees", "/departments").permitAll()
                    //.antMatchers(HttpMethod.POST, "/employees", "/departments").permitAll()
                    //.antMatchers(HttpMethod.DELETE, "/employees/{id}", "/departments").permitAll()
                    .anyRequest().authenticated();
        }
    }
}
