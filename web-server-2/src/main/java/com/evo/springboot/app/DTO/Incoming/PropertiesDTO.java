package com.evo.springboot.app.DTO.Incoming;

import java.util.List;

public class PropertiesDTO {
    private List<ReqSlotDTO> reqSlots;
    private List<RuleWeightDTO> rulesWeights;
    private List<String> activeEmployeesIds;

    public List<ReqSlotDTO> getReqSlots() {
        return reqSlots;
    }

    public void setReqSlots(List<ReqSlotDTO> reqSlots) {
        this.reqSlots = reqSlots;
    }

    public List<RuleWeightDTO> getRulesWeights() {
        return rulesWeights;
    }

    public void setRulesWeights(List<RuleWeightDTO> rulesWeights) {
        this.rulesWeights = rulesWeights;
    }

    public List<String> getActiveEmployeesIds() {
        return activeEmployeesIds;
    }

    public void setActiveEmployeesIds(List<String> activeEmployeesIds) {
        this.activeEmployeesIds = activeEmployeesIds;
    }
}
