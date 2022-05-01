package BusinessLogic;

import Arrangement.ArrangementProperties;
import Model.Company;
import Model.Employee.Employee;
import Model.Range;
import Model.Role;
import Model.Slot.ReqSlot;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleSlots.RuleSlots;

import java.time.LocalDateTime;
import java.util.*;

public class Test {
    public static void main(String[] args) {
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


        // ******** START NEW ARRANGEMENT ******* //
        businessLogic.startNewArrangement(company);


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

        List<Employee> activeEmployees = businessLogic.getAllEmployees(company);
        Map<IRule, Double> ruleWeights = new HashMap<IRule, Double>() {{
           put(new RuleSlots(), 1.0);
        }};

        ArrangementProperties arrangementProperties = new ArrangementProperties(
            reqSlots, activeEmployees, ruleWeights
        );

        businessLogic.setArrangementProperties(company, arrangementProperties);
    }
}
