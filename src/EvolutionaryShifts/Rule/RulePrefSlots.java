package EvolutionaryShifts.Rule;

import EvolutionaryShifts.*;
import EvolutionaryShifts.Arrangement.Arrangement;
import EvolutionaryShifts.Rule.Rule;

public class RulePrefSlots extends Rule
{
    protected SlotPreference m_Preferences;
    //Map<Employee, ArrayList<Slot>> kind of prop

    public RulePrefSlots(SlotPreference employeePreferences) {
        m_Preferences = employeePreferences;
    }

    @Override
    public  void setM_Config(Preference preference)
    {
        m_Preferences = (SlotPreference) preference;
    }

    @Override
    public double Execute(Arrangement arrangeShifts)
    {
        double goodShifts = 0;

        for (Shift shift:arrangeShifts.getShifts())
        {
            Employee employee = shift.getEmployee();

            for (Slot slot : m_Preferences.get().get(employee))
            {
                if(slot == shift.getSlot())
                {
                    goodShifts ++;
                }
            }
        }

        return (goodShifts / arrangeShifts.getShifts().size()) * 100;
    }
}
