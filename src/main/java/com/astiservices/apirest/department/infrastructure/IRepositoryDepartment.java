/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.astiservices.apirest.department.infrastructure;

import com.astiservices.apirest.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Asti Consultoria de Software
 */
public interface IRepositoryDepartment extends JpaRepository<Department,Long>{
    
}
