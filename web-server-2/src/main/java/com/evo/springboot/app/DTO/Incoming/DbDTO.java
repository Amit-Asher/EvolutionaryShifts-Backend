package com.evo.springboot.app.DTO.Incoming;

import java.util.List;

public class DbDTO {
    private CompanyDTO company;
    private List<RoleDTO> roles;
    private List<NewEmployeeDTO> employees;
    private PropertiesDTO properties;
    private List<EmployeePreferencesDTO> preferences;
    private AlgorithmConfigDTO evolution;

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<NewEmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<NewEmployeeDTO> employees) {
        this.employees = employees;
    }

    public PropertiesDTO getProperties() {
        return properties;
    }

    public void setProperties(PropertiesDTO properties) {
        this.properties = properties;
    }

    public List<EmployeePreferencesDTO> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<EmployeePreferencesDTO> preferences) {
        this.preferences = preferences;
    }

    public AlgorithmConfigDTO getEvolution() {
        return evolution;
    }

    public void setEvolution(AlgorithmConfigDTO evolution) {
        this.evolution = evolution;
    }
}