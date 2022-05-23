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

import java.util.Map;
import java.util.Set;
import java.util.List;

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
                            Employee employee)
    {
        Company company = name2Company.get(compName);
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

    public void addNewRole(String compName, Role role)
    {
        Company company = name2Company.get(compName);
        company.addRole(role);
    }

    public void removeRole(String compName, Role role)
    {
        Company company = name2Company.get(compName);
        company.removeRole(role);
    }

    public List<Role> getAllRoles(Company company) {
        return company.getAllRoles();
    }

    public List<Employee> getAllEmployees(Company company) {
        return company.getAllEmployees();
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

    public ArrangementProperties getArrangementProperties(Company company) {
        return company.getArrangementManager().getCurrArrangementProp();
    }

    /*************** WAIT EMP REQ ***************/

    public void setEmployeePreference(String compName, EmployeePreferences employeePreferences) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().setEmployeePreference(employeePreferences);
    }

    // JUST FOR TESTING
    public List<RuleSlotsPreference> getEmployeeSlotsPreference(String compName) {
        Company company = name2Company.get(compName);
        return company.getArrangementManager().getEmployeesSlotsPreferences();
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

    public EvolutionStatus getSolution(Company company) {
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
//    public void createTicket(String compName,
//                                   Employee employee,
//                                   String employeeMessage) {
//        Company company = name2Company.get(compName);
//        company.getArrangementManager().createTicket(
//                employee,
//                employeeMessage
//        );
//    }
//
//    public void closeTicket(String compName, String ticketId) {
//        Company company = name2Company.get(compName);
//        company.getArrangementManager().closeTicket(ticketId);
//    }

//    public void closeTicket(Company company, String ticketId) {
//        company.getArrangementManager().closeTicket(ticketId);
//    }

//    public void setArrangement(String compName, Arrangement arrangement) {
//        Company company = name2Company.get(compName);
//        // manager method to set new arrangement after review tickets
//        company.getArrangementManager().setArrangement(arrangement);
//
//    }

    public void finishArrangement(String compName) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().finishArrangement();
    }

    /********************** FINISH ********************/
}
