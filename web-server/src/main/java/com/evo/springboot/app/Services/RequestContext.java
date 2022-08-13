package com.evo.springboot.app.Services;

public class RequestContext {
    private String company;
    private String employee;

    public RequestContext(String company, String employee) {
        this.company = company;
        this.employee = employee;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
