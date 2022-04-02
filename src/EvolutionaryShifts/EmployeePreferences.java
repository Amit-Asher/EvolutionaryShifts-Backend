package EvolutionaryShifts;

import java.util.Map;

public class EmployeePreferences<T>
{
    //            ruleName
    protected Map<String, T> preferences;
    protected Employee employee;

    public Map<String, T> getPreferences() {
        return preferences;
    }
}
