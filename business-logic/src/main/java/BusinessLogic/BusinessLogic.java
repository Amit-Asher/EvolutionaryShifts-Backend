package BusinessLogic;

import Algorithm.AlgorithmConfig;
import Algorithm.EvolutionStatus;
import Arrangement.ArrangementProperties;
import Model.Company;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Model.Role;
import Model.Slot.ReqSlot;
import Rule.RuleSlots.RuleSlotsPreference;
import Schemas.SchemaFactory;
import Schemas.SchemaFamily;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class BusinessLogic {
    protected Map<String, Company> name2Company = new HashMap<>();

    // TODO: REPLACE THIS LOGIC WITH AUTHENTICATION
    public static String staticCompName = "Apple";

    private static BusinessLogic instance = null;

    private BusinessLogic() {

    }

    public static BusinessLogic getInstance() {
        if (instance == null) {
            instance = new BusinessLogic();
            instance.addCompany("Apple");
        }
        return instance;
    }

    public void cleanDb(String compName) {
        name2Company.put(compName, new Company(compName));
    }

    public Company getCompanyByName(String compName) {
        return name2Company.get(compName);
    }

    public void addCompany(String compName){
        Company comp = new Company(compName);
        name2Company.put(compName,comp);
    }
    /************** SET PROPS **************/

    public void addEmployee(String compName, Employee employee)
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

    public Set<Role> getAllRoles(String compName) {
        Company company = name2Company.get(compName);
        return company.getAllRoles();
    }

    public List<Employee> getAllEmployees(String compName) {
        Company company = name2Company.get(compName);
        return company.getAllEmployees();
    }

    public List<Employee> getActiveEmployees(String compName) {
        Company company = name2Company.get(compName);
        return company.getArrangementManager().getActiveEmployees();
    }

    public Set<Role> getRoles(String compName){
        Company company = name2Company.get(compName);
        return company.getAllRoles();
    }

    public SchemaFamily getRulesOptions() {
        return SchemaFactory.getSchemas(SchemaFactory.SchemaType.RULES);
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

    public ArrangementProperties getArrangementProperties(String compName) {
        Company company = name2Company.get(compName);
        return company.getArrangementManager().getCurrArrangementProp();
    }

    /*************** WAIT EMP REQ ***************/

    public void setEmployeePreference(String compName, EmployeePreferences employeePreferences) throws JSONException {
        Company company = name2Company.get(compName);
        company.getArrangementManager().setEmployeePreference(employeePreferences);
    }

    // JUST FOR TESTING
    public List<RuleSlotsPreference> getEmployeeSlotsPreference(String compName) {
        Company company = name2Company.get(compName);
        return company.getArrangementManager().getEmployeesSlotsPreferences();
    }

    public List<ReqSlot> getReqSlots(String compName) {
        Company company = name2Company.get(compName);
        return company.getArrangementManager().getReqSlots();
    }

    public void blockEmployeesToSetPref(String compName) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().BlockEmployeesToSetPref();
    }


    /**************** SOLVING *******************/

    public List<SchemaFamily> getSchemasFamilies() {
        List<SchemaFamily> schemaFamilies = new ArrayList<>();
        schemaFamilies.add(SchemaFactory.getSchemas(SchemaFactory.SchemaType.MUTATIONS));
        schemaFamilies.add(SchemaFactory.getSchemas(SchemaFactory.SchemaType.CROSSOVERS));
        schemaFamilies.add(SchemaFactory.getSchemas(SchemaFactory.SchemaType.SELECTIONS));
        schemaFamilies.add(SchemaFactory.getSchemas(SchemaFactory.SchemaType.TERM_CONDS));
        return schemaFamilies;
    }

    public void startAlgorithm(String compName, AlgorithmConfig algorithmConfig) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().startAlgorithm(algorithmConfig);
    }

    public EvolutionStatus getSolution(String compName) {
        Company company = name2Company.get(compName);
        return new EvolutionStatus(
                company.getArrangementManager().getCurArrangementSolution(),
                !company.getArrangementManager().isEvolutionWorkerAlive()
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






    /********************** ALT DATABASE ********************/





}
