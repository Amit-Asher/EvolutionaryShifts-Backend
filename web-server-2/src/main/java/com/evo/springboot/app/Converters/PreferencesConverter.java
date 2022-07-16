package com.evo.springboot.app.Converters;

import BusinessLogic.BusinessLogic;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import com.evo.springboot.app.DTO.Incoming.PreferencesDTO;

import java.util.List;

public class PreferencesConverter {

    public static EmployeePreferences convert(PreferencesDTO preferencesDTO) {
        // todo: make it stateless! yaks

        List<Employee> allEmployees = BusinessLogic.getInstance().getAllEmployees(BusinessLogic.staticCompName);
        Employee employee = allEmployees.stream()
                // todo: we should filter by id, not name
                .filter(employee1 ->
                        employee1.getFullName().equals(preferencesDTO.getEmployeeName()))
                .findFirst()
                .orElse(null);

        return new EmployeePreferences(
                employee,
                preferencesDTO.getPreferences()
        );
    }
}