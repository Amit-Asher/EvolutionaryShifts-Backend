package Model;

import Arrangement.Arrangement;
import Arrangement.ArrangementManager;
import Arrangement.ArrangementProperties;
import Model.Employee.Employee;

import java.util.*;

public class Company
{
    protected String m_Name;
    protected Set<Role> m_Roles = new HashSet<>();
    protected Map<String, Employee> m_Id2Employee = new HashMap<>();
    protected Map<String, Employee> m_ID2Manager = new HashMap<>();
    protected ArrangementManager m_ArrangementManager;


    protected ArrayList<ArrangementProperties> m_SavedProperties;
    protected Map<Date, Arrangement> m_History;

    public void addRole(Role role){
        m_Roles.add(role);
    }
    public void removeRole(Role role){
        m_Roles.remove(role);
    }
    public void addEmployee(Employee employee){
        m_Id2Employee.put(employee.getID(), employee);
    }
    public void removeEmployee(String employeeID){
        if(m_Id2Employee.containsKey(employeeID))
            m_Id2Employee.remove(employeeID);

        if(m_ID2Manager.containsKey(employeeID))
            m_ID2Manager.remove(employeeID);
    }
    public void removeManager(String employeeID){
        if(m_ID2Manager.containsKey(employeeID))
            m_ID2Manager.remove(employeeID);
    }
    public void setAsManager(String employeeID)
    {
        m_ID2Manager.put(employeeID, m_Id2Employee.get(employeeID));
    }

    public ArrangementManager getArrangementManager() {
        return m_ArrangementManager;
    }
}