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
    protected Set<Role> roles = new HashSet<>();
    protected Map<String, Employee> m_Id2Employee = new HashMap<>();
    protected Map<String, Employee> m_ID2Manager = new HashMap<>();
    protected ArrangementManager m_ArrangementManager = null;

    protected ArrayList<ArrangementProperties> m_SavedProperties;
    protected Map<Date, Arrangement> m_History;

    public Company(String name) {
        this.name = name;
    }

    public void updatePasswordForEmp(String employeeID,  String newPassword){
        m_Id2Employee.get(employeeID).setPassword(newPassword);
    }

    public void updateDataForEmp(String employeeID, String typeData, String data){
        Employee employee =  m_Id2Employee.get(employeeID);

        switch (typeData){
            case "EMAIL":
                employee.setEmail(data);
                break;
            case "PHONE":
                employee.setPhoneNumber(data);
                break;
            case "PASSWORD":
                employee.setPassword(data);
                break;
            case "FULL_NAME":
                employee.setFullName(data);
                break;
            default:
                throw new IllegalArgumentException("type data to change in employee was not valid");
        }
    }



    public void addRole(Role role){
        boolean found = roles.stream().anyMatch(existingRole -> existingRole.name.equals(role.name));
        if (!found) {
            this.roles.add(role);
        }
    }
    public void removeRole(Role role){
        this.roles.removeIf(existingRole -> existingRole.name.equals(role.name));
    }

    public void removeRoleFromEmp(Role role, String employeeID){
        this.m_Id2Employee.get(employeeID).removeRole(role);
    }

    public void addRoleToEmp(Role role, String employeeID) {
        this.m_Id2Employee.get(employeeID).addRole(role);
    }

    public Set<Role> getAllRoles() {
        return this.roles;
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