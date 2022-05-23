package BusinessLogic;

import Algorithm.AlgorithmConfig;
import Algorithm.EvolutionStatus;
import Arrangement.Arrangement;
import Arrangement.ArrangementProperties;
import Model.Company;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Model.Role;
import Arrangement.ArrangementStatus;

import java.util.Map;
import java.util.Set;

public class BusinessLogic {
    protected Map<String, Company> name2Company;
    // sanity check for git

    private static BusinessLogic instance = null;

    private BusinessLogic() {

    }


    public static BusinessLogic getInstance() {
        if (instance == null) {
            instance = new BusinessLogic();
        }
        return instance;
    }

    public void addCompany(String compName){
        Company comp = new Company(compName);
        name2Company.put(compName,comp);
    }
    /************** SET PROPS **************/

    public void addEmployee(String compName,
                            String employeeName,
                            String phoneNumber,
                            Set<Role> fitRoles)
    {
        Company company = name2Company.get(compName);
        Employee employee = new Employee(employeeName,
                phoneNumber,
                fitRoles);

        company.addEmployee(employee);
    }
    public void removeEmployee(String compName, String employeeID)
    {
        Company company = name2Company.get(compName);
        company.removeEmployee(employeeID);
    }
    public void setAsManager(String compName, String employeeID)
    {
        Company company = name2Company.get(compName);
        company.setAsManager(employeeID);
    }
    public void removeManager(String compName, String employeeID)
    {
        Company company = name2Company.get(compName);
        company.removeManager(employeeID);
    }

    public void addNewRole(String compName, String roleName)
    {
        Company company = name2Company.get(compName);
        Role role = new Role(roleName);
        company.addRole(role);
    }
    public void removeRole(String compName, String roleName)
    {
        Company company = name2Company.get(compName);
        Role role = new Role(roleName);
        company.removeRole(role);
    }

    public Set<Role> getRoles(String compName){
        Company company = name2Company.get(compName);
        return company.getRoles();
    }

    public void startNewArrangement(String compName) {
        Company company = name2Company.get(compName);
        company.startNewArrangement();
    }

    public void setArrangementProperties(String compName,
                                         ArrangementProperties arrangementProperties) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().setCurrArrangementProp(arrangementProperties);
    }

    /*************** WAIT EMP REQ ***************/

    public void setEmployeePreference(String compName, EmployeePreferences employeePreferences) {
        Company company = name2Company.get(compName);
        try {
            company.getArrangementManager().updateEmployeePreference(employeePreferences);
        } catch (Exception exception) {
            throw exception;
        }
    }

    public void blockEmployeesToSetPref(String compName) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().BlockEmployeesToSetPref();
    }


    /**************** SOLVING *******************/

    public void startAlgorithm(String compName, AlgorithmConfig algorithmConfig) {
        Company company = name2Company.get(compName);
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

    public EvolutionStatus getSolution(String compName) {
        Company company = name2Company.get(compName);
        // todo: support getHistory
        return new EvolutionStatus(
                company.getArrangementManager().getCurArrangementSolution(),
                false // todo: check if thread finished
        );
    }

    public void publishArrangement(String compName) {
        Company company = name2Company.get(compName);
        // manager operation
        company.getArrangementManager().publishArrangement();
    }

    /*************** WAIT EMP APPROVAL *****************/

    // declineArrangement
    public void createTicket(String compName,
                                   Employee employee,
                                   String employeeMessage) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().createTicket(
                employee,
                employeeMessage
        );
    }

    public void closeTicket(String compName, String ticketId) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().closeTicket(ticketId);
    }

    // todo: optional- set employee preferences

    public void setArrangement(String compName, Arrangement arrangement) {
        Company company = name2Company.get(compName);
        // manager method to set new arrangement after review tickets
        // todo: impl
        company.getArrangementManager().setArrangement(arrangement);

    }

    public void finishArrangement(String compName) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().finishArrangement();
    }

    /********************** FINISH ********************/
}
