package Model.Internals;

import BusinessLogic.BusinessLogic;
import Model.Company;
import Model.Employee.Employee;
import Model.Role;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;

public class EmployeesTest {

    @Test
    public void addEmployeesTest() {
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
        Employee waiter5 = new Employee("waiter5Name", "111", new HashSet<Role>() {{
            add(waiter);
        }});
        Employee waiter6 = new Employee("waiter6Name", "111", new HashSet<Role>() {{
            add(waiter);
            add(barman);
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
        Employee shiftManager2 = new Employee("shiftManager2Name", "111", new HashSet<Role>() {{
            add(shiftManager);
            add(host);
        }});

        businessLogic.addEmployee(compName, waiter1);
        businessLogic.addEmployee(compName, waiter2);
        businessLogic.addEmployee(compName, waiter3);
        businessLogic.addEmployee(compName, waiter4);
        businessLogic.addEmployee(compName, waiter5);
        businessLogic.addEmployee(compName, waiter6);
        businessLogic.addEmployee(compName, chef1);
        businessLogic.addEmployee(compName, chef2);
        businessLogic.addEmployee(compName, chef3);
        businessLogic.addEmployee(compName, barman1);
        businessLogic.addEmployee(compName, barman2);
        businessLogic.addEmployee(compName, barman3);
        businessLogic.addEmployee(compName, host1);
        businessLogic.addEmployee(compName, host2);
        businessLogic.addEmployee(compName, shiftManager1);
        businessLogic.addEmployee(compName, shiftManager2);


        System.out.println("******** EMPLOYEES *********");
        businessLogic.getAllEmployees(compName)
                .stream().sorted(Comparator.comparing(Employee::getFullName))
                .forEach((employee)-> {
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
