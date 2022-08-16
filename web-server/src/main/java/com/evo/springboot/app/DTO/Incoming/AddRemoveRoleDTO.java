package com.evo.springboot.app.DTO.Incoming;

import org.springframework.web.bind.annotation.RequestParam;

public class AddRemoveRoleDTO {
    private String roleName;
    private String employeeID;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
