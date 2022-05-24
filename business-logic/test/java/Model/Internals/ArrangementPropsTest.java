package Model.Internals;

import Arrangement.ArrangementProperties;
import BusinessLogic.BusinessLogic;
import Model.Company;
import Model.Employee.Employee;
import Model.Range;
import Model.Role;
import Model.Slot.ReqSlot;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleSlots.RuleSlots;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ArrangementPropsTest {
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



        // ******** START NEW ARRANGEMENT ******* //
        businessLogic.startNewArrangement(compName);


        // ******** SET ARRANGEMENT PROPS ******* //
        // arrangement dates: 22.5.2022 (sunday) -> 26.5.2022 (thursday)
        // NOTE: All days have the same requirements
        List<ReqSlot> reqSlots = new ArrayList<ReqSlot>() {{
            // ******************** SUNDAY MORNING 22.5.2022 - 08:00-16:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 8, 0),
                            LocalDateTime.of(2022, 5, 22, 16, 0)),
                    waiter,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 7, 0),
                            LocalDateTime.of(2022, 5, 22, 16, 0)),
                    chef,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 8, 0),
                            LocalDateTime.of(2022, 5, 22, 16, 0)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 8, 0),
                            LocalDateTime.of(2022, 5, 22, 16, 0)),
                    barman,
                    new Range(1)));

            // ******************** SUNDAY EVENING 22.5.2022 - 16:00-00:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
                            LocalDateTime.of(2022, 5, 22, 23, 59)),
                    waiter,
                    new Range(3)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
                            LocalDateTime.of(2022, 5, 22, 23, 59)),
                    chef,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
                            LocalDateTime.of(2022, 5, 22, 23, 59)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
                            LocalDateTime.of(2022, 5, 22, 23, 59)),
                    barman,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
                            LocalDateTime.of(2022, 5, 22, 22, 0)),
                    host,
                    new Range(1)));

            // ******************** MONDAY MORNING 23.5.2022 - 08:00-16:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 8, 0),
                            LocalDateTime.of(2022, 5, 23, 16, 0)),
                    waiter,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 7, 0),
                            LocalDateTime.of(2022, 5, 23, 16, 0)),
                    chef,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 8, 0),
                            LocalDateTime.of(2022, 5, 23, 16, 0)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 8, 0),
                            LocalDateTime.of(2022, 5, 23, 16, 0)),
                    barman,
                    new Range(1)));

            // ******************** MONDAY EVENING 23.5.2022 - 16:00-00:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
                            LocalDateTime.of(2022, 5, 23, 23, 59)),
                    waiter,
                    new Range(3)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
                            LocalDateTime.of(2022, 5, 23, 23, 59)),
                    chef,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
                            LocalDateTime.of(2022, 5, 23, 23, 59)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
                            LocalDateTime.of(2022, 5, 23, 23, 59)),
                    barman,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
                            LocalDateTime.of(2022, 5, 23, 22, 0)),
                    host,
                    new Range(1)));

            // ******************** TUESDAY MORNING 24.5.2022 - 08:00-16:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 8, 0),
                            LocalDateTime.of(2022, 5, 24, 16, 0)),
                    waiter,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 7, 0),
                            LocalDateTime.of(2022, 5, 24, 16, 0)),
                    chef,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 8, 0),
                            LocalDateTime.of(2022, 5, 24, 16, 0)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 8, 0),
                            LocalDateTime.of(2022, 5, 24, 16, 0)),
                    barman,
                    new Range(1)));

            // ******************** TUESDAY EVENING 24.5.2022 - 16:00-00:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
                            LocalDateTime.of(2022, 5, 24, 23, 59)),
                    waiter,
                    new Range(3)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
                            LocalDateTime.of(2022, 5, 24, 23, 59)),
                    chef,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
                            LocalDateTime.of(2022, 5, 24, 23, 59)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
                            LocalDateTime.of(2022, 5, 24, 23, 59)),
                    barman,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
                            LocalDateTime.of(2022, 5, 24, 22, 0)),
                    host,
                    new Range(1)));

            // ******************** WEDNESDAY MORNING 25.5.2022 - 08:00-16:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 8, 0),
                            LocalDateTime.of(2022, 5, 25, 16, 0)),
                    waiter,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 7, 0),
                            LocalDateTime.of(2022, 5, 25, 16, 0)),
                    chef,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 8, 0),
                            LocalDateTime.of(2022, 5, 25, 16, 0)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 8, 0),
                            LocalDateTime.of(2022, 5, 25, 16, 0)),
                    barman,
                    new Range(1)));

            // ******************** WEDNESDAY EVENING 25.5.2022 - 16:00-00:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
                            LocalDateTime.of(2022, 5, 25, 23, 59)),
                    waiter,
                    new Range(3)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
                            LocalDateTime.of(2022, 5, 25, 23, 59)),
                    chef,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
                            LocalDateTime.of(2022, 5, 25, 23, 59)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
                            LocalDateTime.of(2022, 5, 25, 23, 59)),
                    barman,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
                            LocalDateTime.of(2022, 5, 25, 22, 0)),
                    host,
                    new Range(1)));

            // ******************** THURSDAY MORNING 26.5.2022 - 08:00-16:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 8, 0),
                            LocalDateTime.of(2022, 5, 26, 16, 0)),
                    waiter,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 7, 0),
                            LocalDateTime.of(2022, 5, 26, 16, 0)),
                    chef,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 8, 0),
                            LocalDateTime.of(2022, 5, 26, 16, 0)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 8, 0),
                            LocalDateTime.of(2022, 5, 26, 16, 0)),
                    barman,
                    new Range(1)));

            // ******************** THURSDAY EVENING 26.5.2022 - 16:00-00:00 *********************** //
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
                            LocalDateTime.of(2022, 5, 26, 23, 59)),
                    waiter,
                    new Range(3)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
                            LocalDateTime.of(2022, 5, 26, 23, 59)),
                    chef,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
                            LocalDateTime.of(2022, 5, 26, 23, 59)),
                    shiftManager,
                    new Range(1)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
                            LocalDateTime.of(2022, 5, 26, 23, 59)),
                    barman,
                    new Range(2)));
            add(new ReqSlot(
                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
                            LocalDateTime.of(2022, 5, 26, 22, 0)),
                    host,
                    new Range(1)));

        }};

        List<Employee> activeEmployees = businessLogic.getAllEmployees(compName);
        Map<IRule, Double> ruleWeights = new HashMap<IRule, Double>() {{
            put(new RuleSlots(), 1.0);
        }};

        ArrangementProperties arrangementProperties = new ArrangementProperties(
                reqSlots, activeEmployees, ruleWeights
        );

        businessLogic.setArrangementProperties(compName, arrangementProperties);


        System.out.println("************** ARRANGEMENT PROPERTIES ******************");
        System.out.println("Manager required the following slots:");
        String leftAlignFormat = "| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n";
        System.out.format("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+%n");
        System.out.format("| Date            | Day             | Role            | Personnel Size  | Start Time      | End Time        |%n");
        System.out.format("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+%n");
        businessLogic.getArrangementProperties(compName).getSlots().forEach(reqSlot -> {
            DateTimeFormatter hmFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.u");
            System.out.format(String.format(leftAlignFormat,
                    reqSlot.getSlot().getStartTime().format(dateFormatter),
                    reqSlot.getSlot().getStartTime().getDayOfWeek(),
                    reqSlot.getRole().m_Name,
                    reqSlot.getPersonnelSize().toString(),
                    reqSlot.getSlot().getStartTime().format(hmFormatter),
                    reqSlot.getSlot().getEndTime().format(hmFormatter)));
        });

        System.out.format("%nManager opened the following rules and weights:%n");
        businessLogic.getArrangementProperties(compName).getRule2weight().forEach((key, value) -> {
            System.out.format("%s: %s%n",
                    key.getName(),
                    value);
        });


        System.out.format("%nManager defined the active employees as follows (participants):%n");
        businessLogic.getArrangementProperties(compName).getActiveEmployees().forEach(employee -> {
            System.out.format("%s%n", employee.getFullName());
        });

        System.out.println("********************************************************");

    }
}
