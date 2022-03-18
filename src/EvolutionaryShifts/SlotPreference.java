package EvolutionaryShifts;

import java.util.ArrayList;
import java.util.Map;

public class SlotPreference extends Preference
{
    protected Map<Employee, ArrayList<Slot>> m_Preferences;

    public Map<Employee, ArrayList<Slot>> get()
    {
        return m_Preferences;
    }
}
