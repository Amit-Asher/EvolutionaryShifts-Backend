//package MainApp;
//
//import Arrangement.ArrangementProperties;
//import com.evo.springboot.bl.BusinessLogic;
//import Model.Employee.Employee;
//import Model.Employee.EmployeePreferences;
//import Model.Range;
//import Model.Role;
//import Model.Slot.ReqSlot;
//import Model.Slot.Slot;
//import Rule.IRule;
//import Rule.RuleFitEmpRole.RuleFitEmpRole;
//import Rule.RuleSlots.RuleSlots;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//public class MockDataLoader {
//
//    public static void loadData() throws JSONException {
//        BusinessLogic businessLogic = BusinessLogic.getInstance();
//
//        String compName = Constants.COMPANY_NAME;
//        businessLogic.addCompany(compName);
//
//        // ******** ADD ROLES ******* //
//        Role waiter = new Role("waiter");
//        Role chef = new Role("chef");
//        Role host = new Role("host");
//        Role barman = new Role("barman");
//        Role shiftManager = new Role("Shift Manager");
//
//        businessLogic.addNewRole(compName, waiter);
//        businessLogic.addNewRole(compName, chef);
//        businessLogic.addNewRole(compName, host);
//        businessLogic.addNewRole(compName, barman);
//        businessLogic.addNewRole(compName, shiftManager);
//
//        // ******** ADD EMPLOYEES ******* //
//        Employee waiter1 = new Employee("waiter1Name", "111", new HashSet<Role>() {{
//            add(waiter);
//        }});
//        Employee waiter2 = new Employee("waiter2Name", "111", new HashSet<Role>() {{
//            add(waiter);
//        }});
//        Employee waiter3 = new Employee("waiter3Name", "111", new HashSet<Role>() {{
//            add(waiter);
//            add(barman);
//        }});
//        Employee waiter4 = new Employee("waiter4Name", "111", new HashSet<Role>() {{
//            add(waiter);
//            add(host);
//        }});
//        Employee waiter5 = new Employee("waiter5Name", "111", new HashSet<Role>() {{
//            add(waiter);
//        }});
//        Employee waiter6 = new Employee("waiter6Name", "111", new HashSet<Role>() {{
//            add(waiter);
//            add(barman);
//        }});
//        Employee chef1 = new Employee("chef1Name", "111", new HashSet<Role>() {{
//            add(chef);
//        }});
//        Employee chef2 = new Employee("chef2Name", "111", new HashSet<Role>() {{
//            add(chef);
//        }});
//        Employee chef3 = new Employee("chef3Name", "111", new HashSet<Role>() {{
//            add(shiftManager);
//            add(chef);
//        }});
//        Employee barman1 = new Employee("barman1Name", "111", new HashSet<Role>() {{
//            add(barman);
//        }});
//        Employee barman2 = new Employee("barman2Name", "111", new HashSet<Role>() {{
//            add(barman);
//        }});
//        Employee barman3 = new Employee("barman3Name", "111", new HashSet<Role>() {{
//            add(barman);
//        }});
//        Employee host1 = new Employee("host1Name", "111", new HashSet<Role>() {{
//            add(host);
//        }});
//        Employee host2 = new Employee("host2Name", "111", new HashSet<Role>() {{
//            add(host);
//        }});
//        Employee shiftManager1 = new Employee("shiftManager1Name", "111", new HashSet<Role>() {{
//            add(shiftManager);
//            add(host);
//        }});
//        Employee shiftManager2 = new Employee("shiftManager2Name", "111", new HashSet<Role>() {{
//            add(shiftManager);
//            add(host);
//        }});
//
//        businessLogic.addEmployee(compName, waiter1);
//        businessLogic.addEmployee(compName, waiter2);
//        businessLogic.addEmployee(compName, waiter3);
//        businessLogic.addEmployee(compName, waiter4);
//        businessLogic.addEmployee(compName, waiter5);
//        businessLogic.addEmployee(compName, waiter6);
//        businessLogic.addEmployee(compName, chef1);
//        businessLogic.addEmployee(compName, chef2);
//        businessLogic.addEmployee(compName, chef3);
//        businessLogic.addEmployee(compName, barman1);
//        businessLogic.addEmployee(compName, barman2);
//        businessLogic.addEmployee(compName, barman3);
//        businessLogic.addEmployee(compName, host1);
//        businessLogic.addEmployee(compName, host2);
//        businessLogic.addEmployee(compName, shiftManager1);
//        businessLogic.addEmployee(compName, shiftManager2);
//
//
//        // ******** START NEW ARRANGEMENT ******* //
//        businessLogic.startNewArrangement(compName);
//
//
//        // ******** SET ARRANGEMENT PROPS ******* //
//        // arrangement dates: 22.5.2022 (sunday) -> 26.5.2022 (thursday)
//        // NOTE: All days have the same requirements
//
//        List<ReqSlot> reqSlots = new ArrayList<ReqSlot>() {{
//            // ******************** SUNDAY MORNING 22.5.2022 - 08:00-16:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 8, 0),
//                            LocalDateTime.of(2022, 5, 22, 16, 0)),
//                    waiter,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 7, 0),
//                            LocalDateTime.of(2022, 5, 22, 16, 0)),
//                    chef,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 8, 0),
//                            LocalDateTime.of(2022, 5, 22, 16, 0)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 8, 0),
//                            LocalDateTime.of(2022, 5, 22, 16, 0)),
//                    barman,
//                    new Range(1)));
//
//            // ******************** SUNDAY EVENING 22.5.2022 - 16:00-00:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
//                            LocalDateTime.of(2022, 5, 22, 23, 59)),
//                    waiter,
//                    new Range(3)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
//                            LocalDateTime.of(2022, 5, 22, 23, 59)),
//                    chef,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
//                            LocalDateTime.of(2022, 5, 22, 23, 59)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
//                            LocalDateTime.of(2022, 5, 22, 23, 59)),
//                    barman,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 22, 16, 0),
//                            LocalDateTime.of(2022, 5, 22, 22, 0)),
//                    host,
//                    new Range(1)));
//
//            // ******************** MONDAY MORNING 23.5.2022 - 08:00-16:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 8, 0),
//                            LocalDateTime.of(2022, 5, 23, 16, 0)),
//                    waiter,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 7, 0),
//                            LocalDateTime.of(2022, 5, 23, 16, 0)),
//                    chef,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 8, 0),
//                            LocalDateTime.of(2022, 5, 23, 16, 0)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 8, 0),
//                            LocalDateTime.of(2022, 5, 23, 16, 0)),
//                    barman,
//                    new Range(1)));
//
//            // ******************** MONDAY EVENING 23.5.2022 - 16:00-00:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
//                            LocalDateTime.of(2022, 5, 23, 23, 59)),
//                    waiter,
//                    new Range(3)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
//                            LocalDateTime.of(2022, 5, 23, 23, 59)),
//                    chef,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
//                            LocalDateTime.of(2022, 5, 23, 23, 59)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
//                            LocalDateTime.of(2022, 5, 23, 23, 59)),
//                    barman,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 23, 16, 0),
//                            LocalDateTime.of(2022, 5, 23, 22, 0)),
//                    host,
//                    new Range(1)));
//
//            // ******************** TUESDAY MORNING 24.5.2022 - 08:00-16:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 8, 0),
//                            LocalDateTime.of(2022, 5, 24, 16, 0)),
//                    waiter,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 7, 0),
//                            LocalDateTime.of(2022, 5, 24, 16, 0)),
//                    chef,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 8, 0),
//                            LocalDateTime.of(2022, 5, 24, 16, 0)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 8, 0),
//                            LocalDateTime.of(2022, 5, 24, 16, 0)),
//                    barman,
//                    new Range(1)));
//
//            // ******************** TUESDAY EVENING 24.5.2022 - 16:00-00:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
//                            LocalDateTime.of(2022, 5, 24, 23, 59)),
//                    waiter,
//                    new Range(3)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
//                            LocalDateTime.of(2022, 5, 24, 23, 59)),
//                    chef,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
//                            LocalDateTime.of(2022, 5, 24, 23, 59)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
//                            LocalDateTime.of(2022, 5, 24, 23, 59)),
//                    barman,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 24, 16, 0),
//                            LocalDateTime.of(2022, 5, 24, 22, 0)),
//                    host,
//                    new Range(1)));
//
//            // ******************** WEDNESDAY MORNING 25.5.2022 - 08:00-16:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 8, 0),
//                            LocalDateTime.of(2022, 5, 25, 16, 0)),
//                    waiter,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 7, 0),
//                            LocalDateTime.of(2022, 5, 25, 16, 0)),
//                    chef,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 8, 0),
//                            LocalDateTime.of(2022, 5, 25, 16, 0)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 8, 0),
//                            LocalDateTime.of(2022, 5, 25, 16, 0)),
//                    barman,
//                    new Range(1)));
//
//            // ******************** WEDNESDAY EVENING 25.5.2022 - 16:00-00:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
//                            LocalDateTime.of(2022, 5, 25, 23, 59)),
//                    waiter,
//                    new Range(3)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
//                            LocalDateTime.of(2022, 5, 25, 23, 59)),
//                    chef,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
//                            LocalDateTime.of(2022, 5, 25, 23, 59)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
//                            LocalDateTime.of(2022, 5, 25, 23, 59)),
//                    barman,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 25, 16, 0),
//                            LocalDateTime.of(2022, 5, 25, 22, 0)),
//                    host,
//                    new Range(1)));
//
//            // ******************** THURSDAY MORNING 26.5.2022 - 08:00-16:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 8, 0),
//                            LocalDateTime.of(2022, 5, 26, 16, 0)),
//                    waiter,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 7, 0),
//                            LocalDateTime.of(2022, 5, 26, 16, 0)),
//                    chef,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 8, 0),
//                            LocalDateTime.of(2022, 5, 26, 16, 0)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 8, 0),
//                            LocalDateTime.of(2022, 5, 26, 16, 0)),
//                    barman,
//                    new Range(1)));
//
//            // ******************** THURSDAY EVENING 26.5.2022 - 16:00-00:00 *********************** //
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
//                            LocalDateTime.of(2022, 5, 26, 23, 59)),
//                    waiter,
//                    new Range(3)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
//                            LocalDateTime.of(2022, 5, 26, 23, 59)),
//                    chef,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
//                            LocalDateTime.of(2022, 5, 26, 23, 59)),
//                    shiftManager,
//                    new Range(1)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
//                            LocalDateTime.of(2022, 5, 26, 23, 59)),
//                    barman,
//                    new Range(2)));
//            add(new ReqSlot(
//                    new Slot(LocalDateTime.of(2022, 5, 26, 16, 0),
//                            LocalDateTime.of(2022, 5, 26, 22, 0)),
//                    host,
//                    new Range(1)));
//
//        }};
//
//        List<Employee> activeEmployees = businessLogic.getAllEmployees(compName);
//        Map<IRule, Double> ruleWeights = new HashMap<IRule, Double>() {{
//            put(new RuleFitEmpRole(), 0.0);
//            put(new RuleSlots(), 1.0);
//        }};
//
//        ArrangementProperties arrangementProperties = new ArrangementProperties(
//                reqSlots, activeEmployees, ruleWeights
//        );
//
//        businessLogic.setArrangementProperties(compName, arrangementProperties);
//
//        // ******** SET EMPLOYEES PREFERENCES ******* //
//
//        // example of perfect solution (100% fitness) according to employees preferences:
//
//        // 2022-05-22 08:00, waiters: waiter1, waiter4
//        // 2022-05-22 16:00, waiters: waiter2, waiter3, waiter5
//        // 2022-05-23 08:00, waiters: waiter1, waiter6
//        // 2022-05-23 16:00, waiters: waiter2, waiter3, waiter4
//        // 2022-05-24 08:00, waiters: waiter3, waiter6
//        // 2022-05-24 16:00, waiters: waiter2, waiter4, waiter5
//        // 2022-05-25 08:00, waiters: waiter1, waiter4
//        // 2022-05-25 16:00, waiters: waiter3, waiter4, waiter5
//        // 2022-05-26 08:00, waiters: waiter1, waiter2
//        // 2022-05-26 16:00, waiters: waiter1, waiter2, waiter3
//
//        // 2022-05-22 07:00, chefs: chef1
//        // 2022-05-22 16:00, chefs: chef2, chef3
//        // 2022-05-23 07:00, chefs: chef2
//        // 2022-05-23 16:00, chefs: chef1, chef3
//        // 2022-05-24 07:00, chefs: chef3
//        // 2022-05-24 16:00, chefs: chef1, chef2
//        // 2022-05-25 07:00, chefs: chef3
//        // 2022-05-25 16:00, chefs: chef2, chef3
//        // 2022-05-26 07:00, chefs: chef1
//        // 2022-05-26 16:00, chefs: chef2, chef3
//
//        // 2022-05-22 08:00, barmans: barman1
//        // 2022-05-22 16:00, barmans: barman2, barman3
//        // 2022-05-23 08:00, barmans: barman3
//        // 2022-05-23 16:00, barmans: barman1, barman2
//        // 2022-05-24 08:00, barmans: barman3
//        // 2022-05-24 16:00, barmans: barman1, barman2
//        // 2022-05-25 08:00, barmans: barman1
//        // 2022-05-25 16:00, barmans: barman2, barman3
//        // 2022-05-26 08:00, barmans: barman2
//        // 2022-05-26 16:00, barmans: barman1, barman3
//
//        // 2022-05-22 16:00, hosts: host1
//        // 2022-05-23 16:00, hosts: host2
//        // 2022-05-24 16:00, hosts: host1
//        // 2022-05-25 16:00, hosts: host2
//        // 2022-05-26 16:00, hosts: host1
//
//        // 2022-05-22 08:00, Shift Managers: shift manager1
//        // 2022-05-22 16:00, Shift Managers: shift manager2
//        // 2022-05-23 08:00, Shift Managers: shift manager1
//        // 2022-05-23 16:00, Shift Managers: shift manager2
//        // 2022-05-24 08:00, Shift Managers: shift manager2
//        // 2022-05-24 16:00, Shift Managers: shift manager1
//        // 2022-05-25 08:00, Shift Managers: shift manager2
//        // 2022-05-25 16:00, Shift Managers: shift manager1
//        // 2022-05-26 08:00, Shift Managers: shift manager2
//        // 2022-05-26 16:00, Shift Managers: shift manager1
//
//        EmployeePreferences waiter1NamePref = new EmployeePreferences(waiter1, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 08:00");
//                        put("endTime", "2022-05-22 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 08:00");
//                        put("endTime", "2022-05-23 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 08:00");
//                        put("endTime", "2022-05-25 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 08:00");
//                        put("endTime", "2022-05-26 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", waiter.name);
//                    }});
//                }});
//            }});
//        }});
//
//        // businessLogic.setEmployeePreference(compName, waiter3NamePref);
//
//        EmployeePreferences waiter2NamePref = new EmployeePreferences(waiter2, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 08:00");
//                        put("endTime", "2022-05-26 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", waiter.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences waiter3NamePref = new EmployeePreferences(waiter3, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 08:00");
//                        put("endTime", "2022-05-24 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", waiter.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences waiter4NamePref = new EmployeePreferences(waiter4, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 08:00");
//                        put("endTime", "2022-05-22 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 08:00");
//                        put("endTime", "2022-05-25 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", waiter.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences waiter5NamePref = new EmployeePreferences(waiter5, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", waiter.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences waiter6NamePref = new EmployeePreferences(waiter6, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 08:00");
//                        put("endTime", "2022-05-23 16:00");
//                        put("role", waiter.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 08:00");
//                        put("endTime", "2022-05-24 16:00");
//                        put("role", waiter.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences chef1NamePref = new EmployeePreferences(chef1, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 07:00");
//                        put("endTime", "2022-05-22 16:00");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 07:00");
//                        put("endTime", "2022-05-26 16:00");
//                        put("role", chef.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences chef2NamePref = new EmployeePreferences(chef2, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 07:00");
//                        put("endTime", "2022-05-23 16:00");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", chef.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences chef3NamePref = new EmployeePreferences(chef3, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 07:00");
//                        put("endTime", "2022-05-24 16:00");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 07:00");
//                        put("endTime", "2022-05-25 16:00");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", chef.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", chef.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences barman1NamePref = new EmployeePreferences(barman1, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 08:00");
//                        put("endTime", "2022-05-22 16:00");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 08:00");
//                        put("endTime", "2022-05-25 16:00");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", barman.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences barman2NamePref = new EmployeePreferences(barman2, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 08:00");
//                        put("endTime", "2022-05-26 16:00");
//                        put("role", barman.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences barman3NamePref = new EmployeePreferences(barman3, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 08:00");
//                        put("endTime", "2022-05-23 16:00");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 08:00");
//                        put("endTime", "2022-05-24 16:00");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", barman.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", barman.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences host1NamePref = new EmployeePreferences(host1, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", host.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", host.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", host.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences host2NamePref = new EmployeePreferences(host2, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", host.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", host.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences shiftManager1NamePref = new EmployeePreferences(shiftManager1, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 08:00");
//                        put("endTime", "2022-05-22 16:00");
//                        put("role", shiftManager.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 08:00");
//                        put("endTime", "2022-05-23 16:00");
//                        put("role", shiftManager.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 16:00");
//                        put("endTime", "2022-05-24 23:59");
//                        put("role", shiftManager.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 16:00");
//                        put("endTime", "2022-05-25 23:59");
//                        put("role", shiftManager.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 16:00");
//                        put("endTime", "2022-05-26 23:59");
//                        put("role", shiftManager.name);
//                    }});
//                }});
//            }});
//        }});
//
//        EmployeePreferences shiftManager2NamePref = new EmployeePreferences(shiftManager2, new JSONObject() {{
//            put("RuleSlots", new JSONObject() {{
//                put("slots", new JSONArray() {{
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-22 16:00");
//                        put("endTime", "2022-05-22 23:59");
//                        put("role", shiftManager.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-23 16:00");
//                        put("endTime", "2022-05-23 23:59");
//                        put("role", shiftManager.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-24 08:00");
//                        put("endTime", "2022-05-24 16:00");
//                        put("role", shiftManager.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-25 08:00");
//                        put("endTime", "2022-05-25 16:00");
//                        put("role", shiftManager.name);
//                    }});
//                    put(new JSONObject() {{
//                        put("startTime", "2022-05-26 08:00");
//                        put("endTime", "2022-05-26 16:00");
//                        put("role", shiftManager.name);
//                    }});
//                }});
//            }});
//        }});
//
//        businessLogic.setEmployeePreference(compName, waiter1NamePref);
//        businessLogic.setEmployeePreference(compName, waiter2NamePref);
//        businessLogic.setEmployeePreference(compName, waiter3NamePref);
//        businessLogic.setEmployeePreference(compName, waiter4NamePref);
//        businessLogic.setEmployeePreference(compName, waiter5NamePref);
//        businessLogic.setEmployeePreference(compName, waiter6NamePref);
//        businessLogic.setEmployeePreference(compName, chef1NamePref);
//        businessLogic.setEmployeePreference(compName, chef2NamePref);
//        businessLogic.setEmployeePreference(compName, chef3NamePref);
//        businessLogic.setEmployeePreference(compName, barman1NamePref);
//        businessLogic.setEmployeePreference(compName, barman2NamePref);
//        businessLogic.setEmployeePreference(compName, barman3NamePref);
//        businessLogic.setEmployeePreference(compName, host1NamePref);
//        businessLogic.setEmployeePreference(compName, host2NamePref);
//        businessLogic.setEmployeePreference(compName, shiftManager1NamePref);
//        businessLogic.setEmployeePreference(compName, shiftManager2NamePref);
//
//        businessLogic.blockEmployeesToSetPref(compName);
//    }
//
//}
