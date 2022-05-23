package Arrangement;

import Model.Employee.Employee;
import Model.Slot.ReqSlot;
import Rule.IRule;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrangementProperties
{
    private List<ReqSlot> m_Slots;
    private List<Employee> m_ActiveEmployees;
    private Map<IRule, Double> m_rule2weight;

    public ArrangementProperties(List<ReqSlot> m_Slots,
                                 List<Employee> m_ActiveEmployees,
                                 Map<IRule, Double> m_rule2weight)
    {
        this.m_Slots = m_Slots;
        this.m_ActiveEmployees = m_ActiveEmployees;
        this.m_rule2weight = m_rule2weight;
    }

    public Set<IRule> getRules() {
        return m_rule2weight.keySet();
    }

    public List<ReqSlot> getM_Slots() {
        return m_Slots;
    }

    public void setM_Slots(List<ReqSlot> m_Slots) {
        this.m_Slots = m_Slots;
    }

    public List<Employee> getM_ActiveEmployees() {
        return m_ActiveEmployees;
    }

    public void setM_ActiveEmployees(List<Employee> m_ActiveEmployees) {
        this.m_ActiveEmployees = m_ActiveEmployees;
    }

    public void setM_rule2weight(Map<IRule, Double> m_rule2weight) {
        this.m_rule2weight = m_rule2weight;
    }

    public Map<IRule, Double> getM_rule2weight() {
        return m_rule2weight;
    }
}
