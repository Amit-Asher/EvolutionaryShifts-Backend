package com.evo.springboot.app.DTO.Outgoing;

import java.util.List;

public class SlotsPreferencesDTO {
    private List<EmpSlotsPreferenceDTO> preferences;

    public SlotsPreferencesDTO(List<EmpSlotsPreferenceDTO> preferences) {
        this.preferences = preferences;
    }

    public List<EmpSlotsPreferenceDTO> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<EmpSlotsPreferenceDTO> preferences) {
        this.preferences = preferences;
    }
}
