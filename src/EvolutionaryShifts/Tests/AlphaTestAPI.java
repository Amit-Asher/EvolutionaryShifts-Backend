package EvolutionaryShifts.Tests;

import EvolutionaryShifts.*;
import EvolutionaryShifts.Arrangement.ArrangementProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlphaTestAPI {
    public static void runTest() {

        List<Role> rolesList = new ArrayList<Role>() {{
            add(new Role("eved"));
            add(new Role("adon"));
            add(new Role("kelev"));
            add(new Role("tabah"));
        }};

        List<Employee> employeeList = new ArrayList<Employee>() {{
                    add(new Employee("hatula", "0549281234", new HashSet<Role>(){{
                        add(rolesList.get(0));
                    }}));
                    add(new Employee("amit", "0549281234", new HashSet<Role>(){{
                        add(rolesList.get(2));
                        add(rolesList.get(3));
                    }}));
                    add(new Employee("andrio", "0549281234", new HashSet<Role>(){{
                        add(rolesList.get(1));
                        add(rolesList.get(2));
                    }}));
                    add(new Employee("shaked", "0549281234", new HashSet<Role>(){{
                        add(rolesList.get(0));
                        add(rolesList.get(1));
                        add(rolesList.get(2));
                    }}));
        }};

        Company company = new Company();
        employeeList.forEach(company::addEmployee);
        rolesList.forEach(company::addRole);
        company.setAsManager(employeeList.get(0).getID());

//        List<Slot> reqslots = new ArrayList<Slot>() {{
//            add()
//        }}

//        ArrangementProperties arrangementProperties = new ArrangementProperties(
//
//        )

//        BusinessLogic.getInstance().addEmployee(
//                "hatula",
//                BusinessLogic.company,
//                "0549281234",
//                new HashSet<>());
//
//        BusinessLogic.getInstance().setAsManager(
//                "12343254",
//                BusinessLogic.company);
//
//        BusinessLogic.getInstance().addEmployee(
//                "shaked",
//                BusinessLogic.company,
//                "0549281234",
//                new HashSet<>());
//
//        BusinessLogic.getInstance().addEmployee(
//                "andrio",
//                BusinessLogic.company,
//                "0549281234",
//                new HashSet<>());
//
//        BusinessLogic.getInstance().addEmployee(
//                "amit",
//                BusinessLogic.company,
//                "0549281234",
//                new HashSet<>());
    }

    public static void main(String[] args) {
        runTest();
    }
}
