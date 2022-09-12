package com.evo.springboot.app.DTO.Outgoing;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TermConditionsDTO {
    private String termCondition;
    private Map<String, String> params;

    public TermConditionsDTO(String termCondition, Map<String, String> params) {
        this.termCondition = termCondition;
        this.params = params;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getTermCondition() {
        return termCondition;
    }

    public void setTermCondition(String termCondition) {
        this.termCondition = termCondition;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
