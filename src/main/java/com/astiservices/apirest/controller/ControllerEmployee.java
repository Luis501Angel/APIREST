package com.astiservices.apirest.controller;

import com.astiservices.apirest.model.Employee;
import com.astiservices.apirest.repository.IRepositoryEmployee;
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
public class ControllerEmployee {
    
    @Autowired
    private IRepositoryEmployee respositoryEmployees;
    
    @RequestMapping(value="/employees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        List<Employee> lstEmployees = respositoryEmployees.findAll();
        return lstEmployees;
    }
    
    @RequestMapping(value="/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable Long id){
        Optional<Employee> employees = respositoryEmployees.findById(id);
        return employees.get();
    }
    
    @RequestMapping(value="/employees", method = RequestMethod.POST)
    public boolean saveEmployee(@RequestBody Employee employee){
        try {
            respositoryEmployees.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @RequestMapping(value="/employee", method = RequestMethod.PUT)
    public boolean updateEmployee(@RequestBody Employee employee){
        try {
            respositoryEmployees.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @RequestMapping(value="/employee/{id}", method = RequestMethod.DELETE)
    public boolean deleteEmployee(@PathVariable Long id){
        try {
            respositoryEmployees.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
