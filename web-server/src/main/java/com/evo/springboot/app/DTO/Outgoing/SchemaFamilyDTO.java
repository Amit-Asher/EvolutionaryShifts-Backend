package com.evo.springboot.app.DTO.Outgoing;

import java.util.List;

public class SchemaFamilyDTO {
    private String family;
    private List<SchemaDTO> schemas;

    public SchemaFamilyDTO(String family, List<SchemaDTO> schemas) {
        this.family = family;
        this.schemas = schemas;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<SchemaDTO> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<SchemaDTO> schemas) {
        this.schemas = schemas;
    }
}
