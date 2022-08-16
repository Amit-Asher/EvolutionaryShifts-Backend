package com.evo.springboot.app.DTO.Outgoing;

import java.util.List;

public class SchemaDTO {
    private String name;
    private List<ParamOfSchemaDTO> params;

    public SchemaDTO(String name, List<ParamOfSchemaDTO> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParamOfSchemaDTO> getParams() {
        return params;
    }

    public void setParams(List<ParamOfSchemaDTO> params) {
        this.params = params;
    }
}
