package com.evo.springboot.bl;

import Algorithm.AlgorithmConfig;
import Algorithm.EvolutionStatus;
import Arrangement.ArrangementProperties;
import Arrangement.Ticket;
import Model.Company;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Model.Role;
import Model.Slot.ReqSlot;
import Rule.RuleSlots.RuleSlotsPreference;
import Schemas.SchemaFactory;
import Schemas.SchemaFamily;
import com.evo.springboot.db.Entities.User;
import com.evo.springboot.db.Repositories.UserRepository;
import com.evo.springboot.db.Users.Credentials;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BusinessLogic {

    @Autowired
    UserRepository userRepo;

    protected Map<String, Company> name2Company = new HashMap<String, Company>() {{
        put(staticCompName, new Company(staticCompName));
    }};

    // TODO: REPLACE THIS LOGIC WITH AUTHENTICATION
    public static String staticCompName = "Apple";

    public static String generatePassword(){
        Integer password = 0;
        Random random = new Random();

        for(int i = 1;i <= 6;i++)
        {
            password*=10;
            password+=random.nextInt(10);
        }

        return password.toString();
    }


    //method for any employee!!!
    //We need to tell the manager about any changes!!!
    public void updateDataForEmp(String compName, String employeeID, String typeData,String data){
        Company company = name2Company.get(compName);
        company.updateDataForEmp(employeeID, typeData, data);
    }

    //method only for manager!!!
    public String generatePasswordForEmp(String compName, String employeeID){
        Company company = name2Company.get(compName);
        String newPassword = generatePassword();
        company.updateDataForEmp(employeeID, "PASSWORD", newPassword);
        return  newPassword;
    }

    public void doSignup(String userEmail, String password) {
        try {
            boolean usernameAlreadyExist = userRepo.existsUserByUserEmail(userEmail);

            if (usernameAlreadyExist) {
                throw new RuntimeException("user email is already exist");
            }

            User user = new User();
            user.setUserId(UUID.randomUUID().toString());
            user.setUserEmail(userEmail);
            user.setUserPassword(password);
            userRepo.save(user);
        } catch (Exception err) {
            throw err;
        }
    }

    public boolean changePassword(String userEmail, String newPassword)
    {
        User user = userRepo.findUserByUserEmail(userEmail);

        if (user == null) {
            return false;
        }

        user.setUserPassword(newPassword);
        userRepo.save(user);

        return  false;
    }

    public boolean doLogin(String username, String password) {
        User user = userRepo.findUserByUserEmailAndAndUserPassword(username, password);
        return user != null;
    }

    @Transactional
    public void doSignout(String userEmail) {
        userRepo.deleteUserByUserEmail(userEmail);
    }

    public void cleanDb(String compName) {
        name2Company.put(compName, new Company(compName));
    }

    public Company getCompanyByName(String compName) {
        return name2Company.get(compName);
    }

    public void addCompany(String compName) {
        Company comp = new Company(compName);
        name2Company.put(compName, comp);
    }

    /************** SET PROPS **************/

    public void addEmployee(String compName, Employee employee) {
        Company company = name2Company.get(compName);
        company.addEmployee(employee);
        String newPassword = generatePassword();
        company.updateDataForEmp(employee.getID(), "PASSWORD", newPassword);
    }

    public void removeEmployee(String compName, String employeeID) {
        Company company = name2Company.get(compName);
        company.removeEmployee(employeeID);
    }

    public void setAsManager(String compName, String employeeID) {
        Company company = name2Company.get(compName);
        company.setAsManager(employeeID);
    }

    public void removeManager(String compName, String employeeID) {
        Company company = name2Company.get(compName);
        company.removeManager(employeeID);
    }

    public void addNewRole(String compName, Role role) {
        Company company = name2Company.get(compName);
        company.addRole(role);
    }

    public void removeRole(String compName, Role role) {
        Company company = name2Company.get(compName);
        company.removeRole(role);
    }

    public void addRoleToEmp(String compName, Role role, String employeeID){
        Company company = name2Company.get(compName);
        company.addRoleToEmp(role, employeeID);
    }

    public void removeRoleFromEmp(String compName, Role role, String employeeID) {
        Company company = name2Company.get(compName);
        company.removeRoleFromEmp(role, employeeID);
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

    public Set<Role> getRoles(String compName) {
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
    public void createTicket(String compName,
                                   Employee employee,
                                   String employeeMessage) {
        Company company = name2Company.get(compName);
        company.getArrangementManager().createTicket(
                employee,
                employeeMessage
        );
    }
    public Map<String, Ticket> getAllTickets(String compName){
        Company company = name2Company.get(compName);
        return company.getArrangementManager().getAllTickets();
    }
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
