package EvolutionaryShifts.Arrangement;

import EvolutionaryShifts.Day;
import EvolutionaryShifts.Employee;
import EvolutionaryShifts.ReqSlot;
import EvolutionaryShifts.Rule.Rule;

import java.util.ArrayList;
import java.util.Map;

public class ArrangementProperties
{
    protected ArrayList<ReqSlot> m_Slots;
    protected ArrayList<Employee> m_ActiveEmployees;
    protected Map<Rule, Double> m_rule2weight;
    protected ArrayList<Day> days;

    public Map<Rule, Double> getM_rule2weight() {
        return m_rule2weight;
    }

    public ArrayList<Day> getDays() { return days; }
    public int getNumOfDays() { return days.size(); }
}
