package EvolutionaryShifts;

import EvolutionaryShifts.Arrangement.ArrangementProperties;

import java.util.Set;

public class BusinessLogic
{
    //protected Map<String, Company> m_ID2Company;


    public void setArrangementProperties(ArrangementProperties arrangementProperties,
                                         Company company)
    {
        company.getArrangementManager().setCurrArrangementProp(arrangementProperties);
    }

    public void setEmployeePreference(EmployeePreferences employeePreferences, Company company)
    {
        try {
            company.getArrangementManager().updateEmployeePreference(employeePreferences);
        }
        catch (Exception exception){
            throw exception;
        }
    }

    public void runAlgorithm(Company company, AlgorithmConfig algorithmConfig)
    {
        company.getArrangementManager().runAlgorithm(algorithmConfig);
    }

    /*
    * todo:
    *  we did SET_PROPS
    *  we did WAIT_EMP_REQ
    *  we stopped at SOLVING
    *   APPROVAL (NOT IMPLEMENTED)
    *   FINISH (NOTE IMPLEMENTED)
    *  build test file. for now hard code rules (polymorphism issue)
    *
    * */


    public void BlockEmployeesToSetPref(Company company)
    {
        company.getArrangementManager().BlockEmployeesToSetPref();
    }

    public void addNewRole(String roleName, Company company)
    {
        Role role = new Role(roleName);
        company.addRole(role);
    }
    public void removeRole(String roleName, Company company)
    {
        Role role = new Role(roleName);
        company.removeRole(role);
    }
    public void addEmployee(String employeeName,
                            Company company,
                            String phoneNumber,
                            Set<Role> fitRoles,
                            Boolean isTrainee)
    {
        Employee employee = new Employee(employeeName,
                phoneNumber,
                fitRoles,
                isTrainee);

        company.addEmployee(employee);
    }
    public void removeEmployee(String employeeID,
                               Company company)
    {
        company.removeEmployee(employeeID);
    }
    public void setAsManager(String employeeID,
                             Company company)
    {
        company.setAsManager(employeeID);
    }
    public void removeManager(String employeeID,
                               Company company)
    {
        company.removeManager(employeeID);
    }
}
