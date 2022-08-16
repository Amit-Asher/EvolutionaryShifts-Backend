package com.evo.springboot.app.DTO.Incoming;

public class UpdateDataDTO {
    private String newData;
    private String employeeID;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newPassword) {
        this.newData = newPassword;
    }
}
