package com.evo.springboot.app.DTO.Outgoing;

public class ShiftDTO {
    private String employeeId;
    private String employeeName;
    private String role;
    private String startTime;
    private String endTime;

    public ShiftDTO(String employeeId, String employeeName, String role, String startTime, String endTime) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
