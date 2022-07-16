package com.evo.springboot.app.DTO.Incoming;

import java.util.List;

public class DbDTO {
    private CompanyDTO company;
    private List<RoleDTO> roles;
    private List<NewEmployeeDTO> employees;
    private PropertiesDTO properties;
    private List<PreferencesDTO> preferences;

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

    public List<PreferencesDTO> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<PreferencesDTO> preferences) {
        this.preferences = preferences;
    }
}