package Arrangement;

import Model.Employee.Employee;

import java.util.UUID;

public class Ticket {
    private String id;
    private TicketStatus status;
    private Employee employee;
    private String comment;

    public Ticket(Employee employee, String comment) {
        this.id = UUID.randomUUID().toString();;
        this.status = TicketStatus.OPEN;
        this.employee = employee;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getComment() {
        return comment;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
