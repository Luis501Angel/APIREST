package com.astiservices.apirest.controller;

import com.astiservices.apirest.model.Department;
import com.astiservices.apirest.repository.IRepositoryDepartment;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asti Consultoria de Software
 */

@RestController
@EnableAutoConfiguration
public class ControllerDepartment {

    @Autowired
    private IRepositoryDepartment repositoryDepartment;

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public List<Department> getAllDepartment() {
        List<Department> lstDeparment = repositoryDepartment.findAll();
        return lstDeparment;
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Department getDepartment(@PathVariable Long id) {
        Optional<Department> department = repositoryDepartment.findById(id);
        return department.get();
    }

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public Boolean saveDepartment(@RequestBody Department department) {
        try {
            repositoryDepartment.save(department);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "/department", method = RequestMethod.PUT)
    public Boolean updateDepartment(@RequestBody Department department) {
        try {
            repositoryDepartment.save(department);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
    public Boolean deleteDepartment(@PathVariable Long id){
      try{
          repositoryDepartment.deleteById(id);
          return true;
      } catch (Exception e) {
          return false;
      }
    }    
}
