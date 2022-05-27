package Mutations;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.*;

public class MutationDupsByEmployee implements EvolutionaryOperator<Arrangement> {

    private final List<Employee> employees;

    public MutationDupsByEmployee(List<Employee> employees) {
        this.employees = employees;
    }

    private boolean isGoodShift(Shift shift) {
        return shift.getEmployee().getFitRoles().contains(shift.getRole());
    }

    @Override
    public List<Arrangement> apply(List<Arrangement> arrangements, Random random) {

        arrangements.forEach(arrangement -> {
            // we dont use Set<Shift> because contains not working as expected
            Set<String> dupIdentifierSet = new HashSet<>();
            Set<Shift> shiftsToMutate = new HashSet<>();
            arrangement.getShifts().forEach(shift -> {
                if (!dupIdentifierSet.contains(shift.toString())) {
                    dupIdentifierSet.add(shift.toString());
                } else {
                    shiftsToMutate.add(shift);
                }
            });

            shiftsToMutate.forEach(shift -> {
                shift.setEmployee(this.employees.get(random.nextInt(this.employees.size())));
            });
        });

        return arrangements;
    }
}
