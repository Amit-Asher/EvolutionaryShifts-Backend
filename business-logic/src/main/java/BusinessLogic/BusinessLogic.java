package BusinessLogic;

import Algorithm.AlgorithmConfig;
import Algorithm.EvolutionStatus;
import Arrangement.Arrangement;
import Arrangement.ArrangementProperties;
import Model.Company;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Model.Role;
import Rule.RuleSlots.RuleSlotsPreference;
import org.json.JSONException;

import java.util.List;

public class BusinessLogic {

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

    public void setEmployeePreference(Company company, EmployeePreferences employeePreferences) throws JSONException {
        company.getArrangementManager().setEmployeePreference(employeePreferences);
    }

    // JUST FOR TESTING
    public List<RuleSlotsPreference> getEmployeeSlotsPreference(Company company) {
        return company.getArrangementManager().getEmployeesSlotsPreferences();
    }

    // todo: maybe we can remove this method and block them automatically on first time manager run algorithm
    public void blockEmployeesToSetPref(Company company) {
        company.getArrangementManager().BlockEmployeesToSetPref();
    }


    /**************** SOLVING *******************/

    public void startAlgorithm(Company company, AlgorithmConfig algorithmConfig) {
        company.getArrangementManager().startAlgorithm(algorithmConfig);
    }

    public EvolutionStatus getSolution(Company company) {
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
//    public void createTicket(Company company,
//                                   Employee employee,
//                                   String employeeMessage) {
//        company.getArrangementManager().createTicket(
//                employee,
//                employeeMessage
//        );
//    }

//    public void closeTicket(Company company, String ticketId) {
//        company.getArrangementManager().closeTicket(ticketId);
//    }

//    public void setArrangement(Company company, Arrangement arrangement) {
//        // manager method to set new arrangement after review tickets
//        company.getArrangementManager().setArrangement(arrangement);
//
//    }

    public void finishArrangement(Company company) {
        company.getArrangementManager().finishArrangement();
    }

    /********************** FINISH ********************/
}
