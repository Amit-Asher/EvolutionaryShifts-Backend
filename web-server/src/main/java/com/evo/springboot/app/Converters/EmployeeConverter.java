package com.evo.springboot.app.Converters;

import com.evo.springboot.bl.BusinessLogic;
import Model.Employee.Employee;
import Model.Role;
import com.evo.springboot.app.DTO.Incoming.NewEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class EmployeeConverter {

    @Autowired
    BusinessLogic businessLogic;

    public Employee convert(NewEmployeeDTO employeeDTO) {

        Set<Role> allRoles = businessLogic.getAllRoles(BusinessLogic.staticCompName);

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

        return employee;
    }
}
