package Arrangement;

import Model.Employee.Employee;
import Model.Slot.ReqSlot;
import Rule.IRule;

import java.util.*;
import java.time.DayOfWeek;

public class ArrangementProperties
{
    private List<ReqSlot> m_Slots;
    private List<Employee> m_ActiveEmployees;
    private Map<IRule, Double> m_rule2weight;
    protected Set<DayOfWeek> days;

    public ArrangementProperties(List<ReqSlot> m_Slots,
                                 List<Employee> m_ActiveEmployees,
                                 Map<IRule, Double> m_rule2weight)
    {
        this.m_Slots = m_Slots;
        this.m_ActiveEmployees = m_ActiveEmployees;
        this.m_rule2weight = m_rule2weight;
        this.days = new HashSet<>();
        for (int i=0; i < m_Slots.size(); i++) {
            this.days.add(this.m_Slots.get(i).getSlot().getDay());
        }
    }

    public Set<IRule> getRules() {
        return m_rule2weight.keySet();
    }

    public List<ReqSlot> getSlots() {
        return m_Slots;
    }

    public void setSlots(List<ReqSlot> m_Slots) {
        this.m_Slots = m_Slots;
    }

    public List<Employee> getActiveEmployees() {
        return m_ActiveEmployees;
    }

    public void setActiveEmployees(List<Employee> m_ActiveEmployees) {
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
