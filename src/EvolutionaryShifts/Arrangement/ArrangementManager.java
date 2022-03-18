package EvolutionaryShifts.Arrangement;

import EvolutionaryShifts.AlgorithmConfig;
import EvolutionaryShifts.Employee;
import EvolutionaryShifts.EmployeePreferences;
import org.uncommons.watchmaker.framework.EvolutionEngine;

import java.util.ArrayList;
import java.util.Map;

public class ArrangementManager
{
    protected ArrangementProperties m_CurrArrangementProp;
    protected ArrangementStatus m_CurrArrangementStatus;
    protected EvolutionEngine<Arrangement> engine;
    protected Map<Employee, EmployeePreferences> m_Employee2Pref;

    protected ArrayList<EmployeePreferences> m_EmployeePreferences;

    public void addEmployeePreference(EmployeePreferences employeePreference)
    {
        m_EmployeePreferences.add(employeePreference);
    }

    public ArrangementStatus getCurrArrangementStatus() {
        return m_CurrArrangementStatus;
    }

    public void setEmployeePreference(Employee employee,
                                      EmployeePreferences employeePreference)
    {
        if(m_CurrArrangementStatus != ArrangementStatus.WAIT_EMP_REQ)
            throw new RuntimeException("Failed to setEmployeePreference\n Current status: " +
                    m_CurrArrangementStatus + " expected: WAIT_EMP_REQ");

        m_Employee2Pref.put(employee, employeePreference);
    }

    public void setCurrArrangementProp(ArrangementProperties m_CurrArrangementProp) {
        this.m_CurrArrangementProp = m_CurrArrangementProp;
        this.m_CurrArrangementStatus = ArrangementStatus.WAIT_EMP_REQ;
    }

    public void BlockEmployeesToSetPref()
    {
        if(this.m_CurrArrangementStatus != ArrangementStatus.WAIT_EMP_REQ)
            return;

        this.m_CurrArrangementStatus = ArrangementStatus.SOLVING;
    }

    public void setCurrArrangementStatus(ArrangementStatus m_CurrArrangementStatus) {
        this.m_CurrArrangementStatus = m_CurrArrangementStatus;
    }

    public void runAlgorithm(AlgorithmConfig algorithmConfig)
    {
    /*
    * todo:
    *
    *
    *
    * create EvolutionThread extends Thread that
    * implements all the logic off creating new engine
    * and run evolve based on algorithmConfig.
    *
    *
    *  */
    }

}
