package EvolutionaryShifts;

import EvolutionaryShifts.Arrangement.Arrangement;
import EvolutionaryShifts.Arrangement.ArrangementProperties;

import java.util.Set;

public class BusinessLogic {
//    protected Map<String, Company> m_ID2Company;
    // sanity check for git

    public static Company company = new Company(); // for testing

    private static BusinessLogic instance = null;

    private BusinessLogic() {

    }

    public static BusinessLogic getInstance() {
        if (instance == null) {
            instance = new BusinessLogic();
        }
        return instance;
    }

    /************** SET PROPS **************/

    public void addEmployee(String employeeName,
                            Company company,
                            String phoneNumber,
                            Set<Role> fitRoles)
    {
        Employee employee = new Employee(employeeName,
                phoneNumber,
                fitRoles);

        company.addEmployee(employee);
    }
    public void removeEmployee(String employeeID, Company company)
    {
        company.removeEmployee(employeeID);
    }
    public void setAsManager(String employeeID, Company company)
    {
        company.setAsManager(employeeID);
    }
    public void removeManager(String employeeID, Company company)
    {
        company.removeManager(employeeID);
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

    public void setArrangementProperties(ArrangementProperties arrangementProperties,
                                         Company company) {
        company.getArrangementManager().setCurrArrangementProp(arrangementProperties);
    }

    /*************** WAIT EMP REQ ***************/

    public void setEmployeePreference(EmployeePreferences employeePreferences, Company company) {
        try {
            company.getArrangementManager().updateEmployeePreference(employeePreferences);
        } catch (Exception exception) {
            throw exception;
        }
    }

    public void blockEmployeesToSetPref(Company company) {
        company.getArrangementManager().BlockEmployeesToSetPref();
    }


    /**************** SOLVING *******************/

    public void startAlgorithm(Company company, AlgorithmConfig algorithmConfig) {
        blockEmployeesToSetPref(company);
        company.getArrangementManager().startAlgorithm(algorithmConfig);
    }

    // todo: optional- pauseAlgorithm()
    // todo: optional- stopAlgorithm()
    // todo: optional- resumeAlgorithm()
    // todo: optional- set engine configuration

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

    public EvolutionStatus getSolution() {
        // todo: impl
        return null;
    }

    public void publishArrangement() {
        // todo: impl
    }

    /*************** WAIT EMP APPROVAL *****************/

    public void approveArrangement(Employee employee) {
        // todo: impl
    }

    public void declineArrangement(String employeeMessage) {
        // todo: impl
    }

    // todo: optional- set employee preferences

    public void setArrangement(Arrangement arrangement) {
        // todo: impl
    }

    public void finishArrangement(String arrangementName) {
        // todo: impl
    }

    /********************** FINISH ********************/

    public void reopenArrangement(String arrangementId) {
        // todo: impl
    }
}
