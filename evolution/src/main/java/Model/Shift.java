package Model;

import Model.Employee.Employee;
import Model.Slot.Slot;

public class Shift
{
    protected Employee m_Employee;
    protected Role m_Role;
    protected Slot m_Slot;

    public Slot getSlot() {return m_Slot;}
    public Employee getEmployee() {return m_Employee;}
    public Role getRole() {return m_Role;}

    public void setSlot(Slot slot) {this.m_Slot = slot;}
    public void setEmployee(Employee employee) {this.m_Employee = employee;}
    public void setRole(Role role) {this.m_Role = role;}
}
