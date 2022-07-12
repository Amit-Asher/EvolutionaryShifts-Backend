package com.evo.springboot.app.Converters;

import Model.Employee.Employee;
import Model.Role;
import com.evo.springboot.app.DTO.EmployeeDTO;

import java.util.HashSet;
import java.util.Set;

public class EmployeeConverterDTO {
    public static Employee convert(EmployeeDTO employeeDTO) {
        Set<Role> roles = new HashSet<>();

        // TODO: VALIDATE ROLES
        employeeDTO.roles.forEach(roleName -> roles.add(new Role(roleName)));

        return new Employee(
                employeeDTO.name,
                employeeDTO.phoneNumber,
                roles);
    }
}
