package com.evo.springboot.app.Converters;

import BusinessLogic.BusinessLogic;
import Model.Employee.Employee;
import Model.Role;
import com.evo.springboot.app.DTO.Incoming.NewEmployeeDTO;
import com.evo.springboot.app.DTO.Outgoing.EmployeeDTO;

import java.util.HashSet;
import java.util.Set;

public class EmployeeConverter {
    public static Employee convert(NewEmployeeDTO employeeDTO) {

        Set<Role> allRoles = BusinessLogic.getInstance().getAllRoles(BusinessLogic.staticCompName);

        // todo: make it stateless! yaks
        Set<Role> rolesToSet = new HashSet<>();
        employeeDTO.getRoles().forEach(roleName -> {

            Role roleToSet = allRoles.stream()
                    .filter(role -> role.name.equals(roleName))
                    .findFirst()
                    .orElse(null);

            rolesToSet.add(roleToSet);
        });

        Employee employee = new Employee(
                employeeDTO.getFullName(),
                employeeDTO.getPhoneNumber(),
                rolesToSet);

        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());

        return employee;
    }
}
