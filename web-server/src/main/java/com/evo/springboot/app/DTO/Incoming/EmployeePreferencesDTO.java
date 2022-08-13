package com.evo.springboot.app.DTO.Incoming;

import org.json.JSONObject;

import java.util.Map;

public class EmployeePreferencesDTO {
    private Map<String, Object> preferences;
    private String employeeId; // todo: we should take it from token!
    private String employeeName;

    public JSONObject getPreferences() {
        return new JSONObject(preferences);
    }

    public void setPreferences(Map<String, Object> preferences) {
        this.preferences = preferences;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
