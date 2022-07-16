package com.evo.springboot.app.DTO.Incoming;

import org.json.JSONObject;

public class EvolutionaryOperatorDTO {
    private String type;
    private JSONObject params;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getParams() {
        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }
}
