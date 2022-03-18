package EvolutionaryShifts.Arrangement;

import EvolutionaryShifts.Day;
import EvolutionaryShifts.Employee;
import EvolutionaryShifts.ReqSlot;

import java.util.ArrayList;

public class ArrangementProperties
{
    protected ArrayList<ReqSlot> m_Slots;
    protected ArrayList<Employee> m_ActiveEmployees;

    protected ArrayList<Day> days;

    public ArrayList<Day> getDays() { return days; }
    public int getNumOfDays() { return days.size(); }
}
