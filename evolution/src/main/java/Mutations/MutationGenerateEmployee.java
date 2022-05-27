package Mutations;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class MutationGenerateEmployee implements EvolutionaryOperator<Arrangement> {

    private final double probability;
    private final int numberOfShiftsToChange;
    private final List<Employee> employees;

    public MutationGenerateEmployee(double probability, int numberOfShiftsToChange, List<Employee> employees) {
        this.probability = probability;
        this.numberOfShiftsToChange = numberOfShiftsToChange;
        this.employees = employees;
    }

    private boolean isGoodShift(Shift shift) {
        return shift.getEmployee().getFitRoles().contains(shift.getRole());
    }

    @Override
    public List<Arrangement> apply(List<Arrangement> arrangements, Random random) {

        arrangements.forEach(arrangement -> {
            for(int i = 0; i < numberOfShiftsToChange; i++) {
                Shift shiftToMutate = arrangement.getShifts().get(random.nextInt(arrangement.size()));
                if (random.nextDouble() < this.probability && !isGoodShift(shiftToMutate)) {
                    shiftToMutate.setEmployee(this.employees.get(random.nextInt(employees.size())));
                }
            }
        });

        return arrangements;
    }
}
