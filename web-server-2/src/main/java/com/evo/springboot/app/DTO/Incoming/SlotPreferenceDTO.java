package com.evo.springboot.app.DTO.Incoming;

import java.time.LocalTime;

public class SlotPreferenceDTO {
    private String role;
    private LocalTime startTime;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    private LocalTime endTime;
}
