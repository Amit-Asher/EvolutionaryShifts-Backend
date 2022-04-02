package EvolutionaryShifts;

import EvolutionaryShifts.Rule.Preference;
import EvolutionaryShifts.Rule.Rule;

import java.util.Map;

public class EmployeePreferences
{
    protected Map<String, Preference> preferences;
    protected Employee employee;

    public Map<String, Preference> getPreferences() {
        return preferences;
    }
}
