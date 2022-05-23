package Model.Internals;

import BusinessLogic.BusinessLogic;
import Model.Company;
import Model.Role;
import org.junit.Test;

public class RolesTest {

    @Test
    public void addRoles() {
        Company company = new Company();
        BusinessLogic businessLogic = BusinessLogic.getInstance();

        // ******** ADD ROLES ******* //
        Role waiter = new Role("waiter");
        Role chef = new Role("chef");
        Role host = new Role("host");
        Role barman = new Role("barman");
        Role shiftManager = new Role("Shift Manager");

        businessLogic.addNewRole(company, waiter);
        businessLogic.addNewRole(company, chef);
        businessLogic.addNewRole(company, host);
        businessLogic.addNewRole(company, barman);
        businessLogic.addNewRole(company, shiftManager);

        System.out.println("******** ROLES *********");
        businessLogic.getAllRoles(company).forEach((role) -> {
            System.out.println(role.m_Name);
        });
        System.out.println("************************");
        System.out.println();
    }
}
