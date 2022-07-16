package Model.Internals;

import BusinessLogic.BusinessLogic;
import Model.Company;
import Model.Role;
import org.junit.Test;

public class RolesTest {

    @Test
    public void addRoles() {
        BusinessLogic businessLogic = BusinessLogic.getInstance();

        String compName = "Apple";
        businessLogic.addCompany(compName);
        
        // ******** ADD ROLES ******* //
        Role waiter = new Role("waiter");
        Role chef = new Role("chef");
        Role host = new Role("host");
        Role barman = new Role("barman");
        Role shiftManager = new Role("Shift Manager");

        businessLogic.addNewRole(compName, waiter);
        businessLogic.addNewRole(compName, chef);
        businessLogic.addNewRole(compName, host);
        businessLogic.addNewRole(compName, barman);
        businessLogic.addNewRole(compName, shiftManager);

        System.out.println("******** ROLES *********");
        businessLogic.getAllRoles(compName).forEach((role) -> {
            System.out.println(role.name);
        });
        System.out.println("************************");
        System.out.println();
    }
}
