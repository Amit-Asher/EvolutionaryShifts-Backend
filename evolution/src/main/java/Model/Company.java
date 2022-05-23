package Model;

import Arrangement.Arrangement;
import Arrangement.ArrangementManager;
import Arrangement.ArrangementProperties;
import Arrangement.ArrangementStatus;
import Model.Employee.Employee;

import java.util.*;

public class Company
{
    protected String name;
    protected Set<Role> m_Roles = new HashSet<>();
    protected Map<String, Employee> m_Id2Employee = new HashMap<>();
    protected Map<String, Employee> m_ID2Manager = new HashMap<>();
    protected ArrangementManager m_ArrangementManager = null;

    protected ArrayList<ArrangementProperties> m_SavedProperties;
    protected Map<Date, Arrangement> m_History;

    public Company(String name) {
        this.name = name;
    }


    public void addRole(Role role){
        this.m_Roles.add(role);
    }
    public void removeRole(Role role){
        this.m_Roles.remove(role);
    }
    public Set<Role> getRoles() {
        return m_Roles;
    }
    public List<Role> getRolesAsList() {
        return new ArrayList<>(this.m_Roles);
    }

    public void addEmployee(Employee employee){
        m_Id2Employee.put(employee.getID(), employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(this.m_Id2Employee.values());
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

    public void startNewArrangement() {
        if (this.m_ArrangementManager != null &&
                !this.m_ArrangementManager.getCurrArrangementStatus().equals(ArrangementStatus.FINISH)) {
            throw new RuntimeException("Failed to start new arrangement \n Current status: " +
                    this.m_ArrangementManager.getCurrArrangementStatus() + " expected: FINISH");
        }

        this.m_ArrangementManager = new ArrangementManager();
    }
}