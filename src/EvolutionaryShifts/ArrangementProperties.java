package EvolutionaryShifts;

import java.util.ArrayList;

public class ArrangementProperties
{
    protected ArrayList<RoleBasedSlot> m_Slots;
    protected ArrayList<Employee> m_ActiveEmployees;

    protected ArrayList<Day> days;

    public ArrayList<Day> getDays() { return days; }
    public int getNumOfDays() { return days.size(); }
}
