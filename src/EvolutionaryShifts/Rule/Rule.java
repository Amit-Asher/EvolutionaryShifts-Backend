package EvolutionaryShifts.Rule;

import EvolutionaryShifts.Arrangement.Arrangement;
import EvolutionaryShifts.Employee;
import EvolutionaryShifts.EmployeePreferences;

import java.util.Map;

public abstract class Rule
{
    //protected String m_Config;
    protected String m_Name;

    public String getM_Name() {
        return m_Name;
    }

    public abstract void addPreference(Preference pref);
    //public Rule(Rule rule) {
     //   m_Config = rule.m_Config;
    //}

//    public abstract void setPreference(Map<Employee, EmployeePreferences> employeePreferencesMap);

    //public String getConfig(){return m_Config;}

    public abstract double Execute(Arrangement arrangement);
}
