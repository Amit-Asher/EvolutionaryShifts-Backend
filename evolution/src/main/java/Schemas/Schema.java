package Schemas;

import java.util.List;

public class Schema {
    private String name;
    private List<ParamOfSchema> params;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParamOfSchema> getParams() {
        return params;
    }

    public void setParams(List<ParamOfSchema> params) {
        this.params = params;
    }
}
