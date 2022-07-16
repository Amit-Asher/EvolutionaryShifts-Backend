package com.evo.springboot.app.DTO.Incoming;

import org.json.JSONObject;

public class PreferencesDTO {
    private JSONObject preferences;
    private String employeeId; // todo: we should take it from token!
    private String employeeName;

    public JSONObject getPreferences() {
        return preferences;
    }

    public void setPreferences(JSONObject preferences) {
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
