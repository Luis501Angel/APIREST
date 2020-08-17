/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.astiservices.apirest.user.infrastructure;

import com.astiservices.apirest.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Asti Consultoria de Software
 */
public interface IRepositoryUser extends JpaRepository<User, Long>{
    
    List<User> findByUsername(String username);
    
}
