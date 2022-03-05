package EvolutionaryShifts;

public class RulePrefSlots extends Rule
{
    protected SlotPreference m_Preferences;
    //Map<Employee, ArrayList<Slot>> kind of prop

    public RulePrefSlots(Rule rule,
                         SlotPreference employeePreferences) {
        super(rule);
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
