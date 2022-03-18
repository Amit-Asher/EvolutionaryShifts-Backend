package EvolutionaryShifts;

public class Shift {
    protected Employee m_Employee;
    protected String m_RoleForEmployee;
    protected RoleBasedSlot m_Slot;

    public RoleBasedSlot getSlot() {return m_Slot;}
    public Employee getEmployee() {return m_Employee;}
    public String getRoleForEmployee() {return m_RoleForEmployee;}

    public void setSlot(RoleBasedSlot slot) {this.m_Slot = slot;}
    public void setEmployee(Employee employee) {this.m_Employee = employee;}
    public void setRoleForEmployee(String roleForEmployee) {this.m_RoleForEmployee = roleForEmployee;}
}
