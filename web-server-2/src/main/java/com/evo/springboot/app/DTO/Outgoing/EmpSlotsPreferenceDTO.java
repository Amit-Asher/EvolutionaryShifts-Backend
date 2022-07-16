package com.evo.springboot.app.DTO.Outgoing;

import java.util.List;

public class EmpSlotsPreferenceDTO {
    private List<PrfSlotDTO> employeeSlots;
    private String employeeId;
    private String employeeName;

    public EmpSlotsPreferenceDTO(List<PrfSlotDTO> slots, String employeeId, String employeeName) {
        this.employeeSlots = slots;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public List<PrfSlotDTO> getEmployeeSlots() {
        return employeeSlots;
    }

    public void setEmployeeSlots(List<PrfSlotDTO> employeeSlots) {
        this.employeeSlots = employeeSlots;
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
