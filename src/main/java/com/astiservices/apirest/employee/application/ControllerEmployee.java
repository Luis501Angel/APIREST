package com.astiservices.apirest.employee.application;

import com.astiservices.apirest.employee.domain.Employee;
import com.astiservices.apirest.employee.infrastructure.IRepositoryEmployee;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class ControllerEmployee {
    
    @Autowired
    private IRepositoryEmployee respositoryEmployees;
    
    @RequestMapping(value="/employees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        return respositoryEmployees.findAll();
    }
    
    @RequestMapping(value="/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable Long id){
        Optional<Employee> employees = respositoryEmployees.findById(id);
        if(employees.isPresent()){
            return employees.get();
        } else {
            return null;
        }
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
    
    @RequestMapping(value="/employees", method = RequestMethod.PUT)
    public boolean updateEmployee(@RequestBody Employee employee){
        try {
            respositoryEmployees.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @RequestMapping(value="/employees/{id}", method = RequestMethod.DELETE)
    public boolean deleteEmployee(@PathVariable Long id){
        try {
            respositoryEmployees.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
