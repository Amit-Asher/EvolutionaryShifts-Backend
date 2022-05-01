package Model.Internals;

import BusinessLogic.BusinessLogic;
import Model.Company;
import Model.Employee.Employee;
import Model.Role;
import org.junit.Test;

import java.util.HashSet;

public class EmployeesTest {

    @Test
    public void addEmployeesTest() {
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

        // ******** ADD EMPLOYEES ******* //
        Employee waiter1 = new Employee("waiter1Name", "111", new HashSet<Role>() {{
            add(waiter);
        }});
        Employee waiter2 = new Employee("waiter2Name", "111", new HashSet<Role>() {{
            add(waiter);
        }});
        Employee waiter3 = new Employee("waiter3Name", "111", new HashSet<Role>() {{
            add(waiter);
            add(barman);
        }});
        Employee waiter4 = new Employee("waiter4Name", "111", new HashSet<Role>() {{
            add(waiter);
            add(host);
        }});
        Employee chef1 = new Employee("chef1Name", "111", new HashSet<Role>() {{
            add(chef);
        }});
        Employee chef2 = new Employee("chef2Name", "111", new HashSet<Role>() {{
            add(chef);
        }});
        Employee chef3 = new Employee("chef3Name", "111", new HashSet<Role>() {{
            add(shiftManager);
            add(chef);
        }});
        Employee barman1 = new Employee("barman1Name", "111", new HashSet<Role>() {{
            add(barman);
        }});
        Employee barman2 = new Employee("barman2Name", "111", new HashSet<Role>() {{
            add(barman);
        }});
        Employee barman3 = new Employee("barman3Name", "111", new HashSet<Role>() {{
            add(barman);
        }});
        Employee host1 = new Employee("host1Name", "111", new HashSet<Role>() {{
            add(host);
        }});
        Employee host2 = new Employee("host2Name", "111", new HashSet<Role>() {{
            add(host);
        }});
        Employee shiftManager1 = new Employee("shiftManager1Name", "111", new HashSet<Role>() {{
            add(shiftManager);
            add(host);
        }});

        businessLogic.addEmployee(company, waiter1);
        businessLogic.addEmployee(company, waiter2);
        businessLogic.addEmployee(company, waiter3);
        businessLogic.addEmployee(company, waiter4);
        businessLogic.addEmployee(company, chef1);
        businessLogic.addEmployee(company, chef2);
        businessLogic.addEmployee(company, chef3);
        businessLogic.addEmployee(company, barman1);
        businessLogic.addEmployee(company, barman2);
        businessLogic.addEmployee(company, barman3);
        businessLogic.addEmployee(company, host1);
        businessLogic.addEmployee(company, host2);
        businessLogic.addEmployee(company, shiftManager1);

        System.out.println("******** EMPLOYEES *********");
        businessLogic.getAllEmployees(company).forEach((employee)-> {
            System.out.println(String.format("name: %s", employee.getFullName()));
            System.out.println(String.format("id: %s", employee.getID()));
            System.out.print("fit roles:");
            employee.getFitRoles().forEach((role) -> {
                System.out.print(String.format(" %s |", role.m_Name));
            });
            System.out.println();
            System.out.println();
        });
        System.out.println("****************************");
        System.out.println();
    }
}
