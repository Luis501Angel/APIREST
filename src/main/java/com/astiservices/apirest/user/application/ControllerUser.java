/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.astiservices.apirest.user.application;

import com.astiservices.apirest.user.domain.User;
import com.astiservices.apirest.user.infrastructure.IRepositoryUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin()
public class ControllerUser {

    @Autowired
    private IRepositoryUser repositoryUser;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestParam("user") String username, @RequestParam("password") String password) {
        List<User> lstUserExits = repositoryUser.findByUsername(username);
        HashMap<String, String> map = new HashMap<>();
        if (lstUserExits.isEmpty()) {
            map.put("message", "El usuario no existe");
            return map;
        } else {
            if (lstUserExits.get(0).getPassword().equals(password)) {
                map.put("message", getJWTToken(username));
                return map;
            }
        }
        map.put("message ", "Password incorrecta");
        return map;
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
                .setExpiration(new Date(System.currentTimeMillis() + 6000000)) // 1 hora
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return token;
    }
}
