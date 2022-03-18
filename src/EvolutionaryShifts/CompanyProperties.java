package EvolutionaryShifts;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CompanyProperties
{
    protected String m_Name;
    protected ArrayList<Role> m_Roles;
    protected ArrayList<Employee> m_Employees;
    protected ArrayList<Employee> m_Managers;
    protected ArrayList<ArrangementProperties> m_SavedProperties;
    protected Map<Date, Arrangement> m_History;
    protected ArrangementManager m_ArrangementManager;

}