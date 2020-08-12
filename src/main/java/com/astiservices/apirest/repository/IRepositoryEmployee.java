/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.astiservices.apirest.repository;

import com.astiservices.apirest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Asti Consultoria de Software
 */
public interface IRepositoryEmployee extends JpaRepository<Employee, Long>{
    
}
