package com.evo.springboot.app.DTO.Outgoing;

public class PrfSlotDTO {
    private String role;
    private String startTime;
    private String endTime;

    public PrfSlotDTO(String role, String startTime, String endTime) {
        this.role = role;
        this.startTime = startTime;
        this.endTime = endTime;
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
