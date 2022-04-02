package EvolutionaryShifts.Arrangement;

import EvolutionaryShifts.Day;
import EvolutionaryShifts.Employee;
import EvolutionaryShifts.ReqSlot;
import EvolutionaryShifts.Rule.IRule;

import java.util.ArrayList;
import java.util.Map;

public class ArrangementProperties
{

    protected ArrayList<ReqSlot> m_Slots;
    protected ArrayList<Employee> m_ActiveEmployees;
    protected Map<IRule, Double> m_rule2weight;
    protected ArrayList<Day> days;

    public ArrangementProperties(ArrayList<ReqSlot> m_Slots,
                                 ArrayList<Employee> m_ActiveEmployees,
                                 Map<IRule, Double> m_rule2weight,
                                 ArrayList<Day> days)
    {
        this.m_Slots = m_Slots;
        this.m_ActiveEmployees = m_ActiveEmployees;
        this.m_rule2weight = m_rule2weight;
        this.days = days;
    }

    public ArrayList<ReqSlot> getM_Slots() {
        return m_Slots;
    }

    public void setM_Slots(ArrayList<ReqSlot> m_Slots) {
        this.m_Slots = m_Slots;
    }

    public ArrayList<Employee> getM_ActiveEmployees() {
        return m_ActiveEmployees;
    }

    public void setM_ActiveEmployees(ArrayList<Employee> m_ActiveEmployees) {
        this.m_ActiveEmployees = m_ActiveEmployees;
    }

    public void setM_rule2weight(Map<IRule, Double> m_rule2weight) {
        this.m_rule2weight = m_rule2weight;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public Map<IRule, Double> getM_rule2weight() {
        return m_rule2weight;
    }

    public ArrayList<Day> getDays() { return days; }
    public int getNumOfDays() { return days.size(); }
}
