package BusinessLogic;

import Algorithm.AlgorithmConfig;
import Algorithm.EvolutionStatus;
import Arrangement.Arrangement;
import Arrangement.ArrangementProperties;
import Model.Company;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Model.Role;

import java.util.List;

public class BusinessLogic {
//    protected Map<String, Company> m_ID2Company;
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

    /************** SET PROPS **************/

    public void addEmployee(Company company,
                            Employee employee)
    {
        company.addEmployee(employee);
    }
    public void removeEmployee(Company company, String employeeID)
    {
        company.removeEmployee(employeeID);
    }
    public void setAsManager(Company company, String employeeID)
    {
        company.setAsManager(employeeID);
    }
    public void removeManager(Company company, String employeeID)
    {
        company.removeManager(employeeID);
    }

    public void addNewRole(Company company, Role role)
    {
        company.addRole(role);
    }
    public void removeRole(Company company, Role role)
    {
        company.removeRole(role);
    }
    public List<Role> getAllRoles(Company company) {
        return company.getAllRoles();
    }

    public List<Employee> getAllEmployees(Company company) {
        return company.getAllEmployees();
    }

    public void startNewArrangement(Company company) {
        company.startNewArrangement();
    }

    public void setArrangementProperties(Company company,
                                         ArrangementProperties arrangementProperties) {
        company.getArrangementManager().setCurrArrangementProp(arrangementProperties);
    }

    public ArrangementProperties getArrangementProperties(Company company) {
        return company.getArrangementManager().getCurrArrangementProp();
    }

    /*************** WAIT EMP REQ ***************/

    public void setEmployeePreference(Company company, EmployeePreferences employeePreferences) {
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

    public EvolutionStatus getSolution(Company company) {
        // todo: support getHistory
        return new EvolutionStatus(
                company.getArrangementManager().getCurArrangementSolution(),
                false // todo: check if thread finished
        );
    }

    public void publishArrangement(Company company) {
        // manager operation
        company.getArrangementManager().publishArrangement();
    }

    /*************** WAIT EMP APPROVAL *****************/

    // declineArrangement
    public void createTicket(Company company,
                                   Employee employee,
                                   String employeeMessage) {
        company.getArrangementManager().createTicket(
                employee,
                employeeMessage
        );
    }

    public void closeTicket(Company company, String ticketId) {
        company.getArrangementManager().closeTicket(ticketId);
    }

    // todo: optional- set employee preferences

    public void setArrangement(Company company, Arrangement arrangement) {
        // manager method to set new arrangement after review tickets
        // todo: impl
        company.getArrangementManager().setArrangement(arrangement);

    }

    public void finishArrangement(Company company) {
        company.getArrangementManager().finishArrangement();
    }

    /********************** FINISH ********************/
}
