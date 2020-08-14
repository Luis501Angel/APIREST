/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.astiservices.apirest.controller;

import com.astiservices.apirest.model.User;
import com.astiservices.apirest.repository.IRepositoryUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asti Consultoria de Software
 */
@RestController
@EnableAutoConfiguration
public class ControllerUser {

    @Autowired
    private IRepositoryUser repositoryUser;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("user") String username, @RequestParam("password") String password) {
        List<User> lstUserExits = repositoryUser.findByUsername(username);
        if (lstUserExits.isEmpty()) {
            return "El usuario no existe";
        } else {
            if (lstUserExits.get(0).getPassword().equals(password)) {
                String token = getJWTToken(username);
                return token;
            }
        }
        return "Contraseña incorrecta";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User signup(@RequestBody User user) {
        return repositoryUser.save(user);
    }

    private String getJWTToken(String username) {
        String secretKey = "asti_secret";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("astiJWT")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // 10 minutes
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
