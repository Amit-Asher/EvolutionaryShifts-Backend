package com.evo.springboot.app.DTO.Incoming;

public class ReqSlotDTO {
    private String startTime;
    private String endTime;
    private String role;
    private RangeDTO personnelSize;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RangeDTO getPersonnelSize() {
        return personnelSize;
    }

    public void setPersonnelSize(RangeDTO personnelSize) {
        this.personnelSize = personnelSize;
    }
}
