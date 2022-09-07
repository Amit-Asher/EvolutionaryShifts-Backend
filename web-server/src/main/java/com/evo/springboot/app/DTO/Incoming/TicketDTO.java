package com.evo.springboot.app.DTO.Incoming;

public class TicketDTO {
    private String empName;
    private String msg;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
