package com.evo.springboot.app.DTO;

import java.util.List;

public class RolesDTO {
    private List<String> names;

    public RolesDTO(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
