package Arrangement;

import Model.Employee.Employee;
import Model.Slot.ReqSlot;
import Rule.IRule;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ArrangementProperties
{

    protected ArrayList<ReqSlot> m_Slots;
    protected ArrayList<Employee> m_ActiveEmployees;
    protected Map<IRule, Double> m_rule2weight;
    protected Set<DayOfWeek> days;

    public ArrangementProperties(ArrayList<ReqSlot> m_Slots,
                                 ArrayList<Employee> m_ActiveEmployees,
                                 Map<IRule, Double> m_rule2weight,
                                 Set<DayOfWeek> days)
    {
        this.m_Slots = m_Slots;
        this.m_ActiveEmployees = m_ActiveEmployees;
        this.m_rule2weight = m_rule2weight;
        this.days = days;
    }

    public ArrayList<ReqSlot> getSlots() {
        return m_Slots;
    }

    public void setSlots(ArrayList<ReqSlot> m_Slots) {
        this.m_Slots = m_Slots;
    }

    public ArrayList<Employee> getActiveEmployees() {
        return m_ActiveEmployees;
    }

    public void setActiveEmployees(ArrayList<Employee> m_ActiveEmployees) {
        this.m_ActiveEmployees = m_ActiveEmployees;
    }

    public void setRule2weight(Map<IRule, Double> m_rule2weight) {
        this.m_rule2weight = m_rule2weight;
    }

    public void setDays(Set<DayOfWeek> days) {
        this.days = days;
    }

    public Map<IRule, Double> getRule2weight() {
        return m_rule2weight;
    }

    public Set<DayOfWeek> getDays() { return days; }
    public int getNumOfDays() { return days.size(); }
}
