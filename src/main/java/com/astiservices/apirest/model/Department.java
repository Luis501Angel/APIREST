package com.astiservices.apirest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author Asti Consultoria de Software
 */

@Entity
@Table(name = "tb_department")
public class Department {
    
    @Id 
    @GeneratedValue(generator = "department_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "department_generator", sequenceName = "s_department_sequence", initialValue = 1, allocationSize = 1)
    private Long idDepartment;

    @NotEmpty()
    @Size(max = 255)
    private String nameDepartment;

    public Department() {
    }

    public Department(Long idDepartment, String nameDepartment) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
    }

    public Department(Long idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
}
