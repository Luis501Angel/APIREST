package com.astiservices.apirest.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author Asti Consultoria de Software
 */

@Entity
@Table(name = "tb_employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(generator = "employee_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "employee_generator", sequenceName = "s_employee_sequence", initialValue = 1, allocationSize = 1)
    private Long idEmployee;

    @NotEmpty()
    @Size(max = 255)
    private String nameEmployee;
    
    @NotEmpty()
    @Size(max = 255)
    private String addressEmployee;
    
    @NotEmpty()
    @Size(max = 10)
    private String phoneNumberEmployee;
    
    @ManyToOne()
    @JoinColumn(foreignKey = @ForeignKey(name = "employee_to_department_fk"), name = "department")
    private Department department;

    public Employee() {
    }
    
    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getAddressEmployee() {
        return addressEmployee;
    }

    public void setAddressEmployee(String addressEmployee) {
        this.addressEmployee = addressEmployee;
    }

    public String getPhoneNumberEmployee() {
        return phoneNumberEmployee;
    }

    public void setPhoneNumberEmployee(String phoneNumberEmployee) {
        this.phoneNumberEmployee = phoneNumberEmployee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
}
