package EvolutionaryShifts;

import org.uncommons.watchmaker.framework.EvolutionEngine;

import java.util.ArrayList;
import java.util.Map;

public class ArrangementManager
{
    protected ArrangementProperties m_CurrArrangementProp;
    protected ArrangementStatus m_CurrArrangementStatus;
    protected EvolutionEngine<Arrangement> engine;
    protected Map<Employee, EmployeePreferences> c;

    protected ArrayList<EmployeePreferences> m_EmployeePreferences;

    public void addEmployeePreference(EmployeePreferences employeePreference)
    {
        m_EmployeePreferences.add(employeePreference);
    }

    public void runAlgorithm()
    {

    }

}
