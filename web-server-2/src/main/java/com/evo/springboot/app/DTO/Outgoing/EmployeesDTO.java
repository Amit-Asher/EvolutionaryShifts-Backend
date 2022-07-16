package com.evo.springboot.app.DTO.Outgoing;

import java.util.List;

public class EmployeesDTO {
    private List<EmployeeDTO> employees;

    public EmployeesDTO(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

}
