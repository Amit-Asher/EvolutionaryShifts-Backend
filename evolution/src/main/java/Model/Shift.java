package Model;

import Model.Employee.Employee;
import Model.Slot.Slot;

public class Shift
{
    protected Employee m_Employee;
    protected Role m_Role;
    protected Slot m_Slot;

    public Shift(Employee m_Employee, Role m_Role, Slot m_Slot) {
        this.m_Employee = m_Employee;
        this.m_Role = m_Role;
        this.m_Slot = m_Slot;
    }

    public Shift(Shift shift) {
        this.m_Employee = new Employee(shift.m_Employee);
        this.m_Role = new Role(shift.m_Role.getName());
        this.m_Slot = new Slot(shift.m_Slot);
    }

    public Slot getSlot() {return m_Slot;}
    public Employee getEmployee() {return m_Employee;}
    public Role getRole() {return m_Role;}

    public void setSlot(Slot slot) {this.m_Slot = slot;}
    public void setEmployee(Employee employee) {this.m_Employee = employee;}
    public void setRole(Role role) {this.m_Role = role;}

    @Override
    public String toString() {
        return m_Employee.getFullName() + m_Slot.toString();
    }
}
