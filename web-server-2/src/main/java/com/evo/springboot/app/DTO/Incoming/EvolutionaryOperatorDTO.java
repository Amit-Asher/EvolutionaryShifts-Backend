package com.evo.springboot.app.DTO.Incoming;

import org.json.JSONObject;

import java.util.Map;

public class EvolutionaryOperatorDTO {
    private String type;
    private Map<String, Object> params;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getParams() {
        return new JSONObject(params);
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
