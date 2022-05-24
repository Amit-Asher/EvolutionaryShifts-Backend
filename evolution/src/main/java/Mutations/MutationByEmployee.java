package Mutations;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class MutationByEmployee implements EvolutionaryOperator<Arrangement> {

    private final double probability;
    private final int numberOfShiftsToChange;
    private final List<Employee> employees;

    public MutationByEmployee(double probability, int numberOfShiftsToChange, List<Employee> employees) {
        this.probability = probability;
        this.numberOfShiftsToChange = numberOfShiftsToChange;
        this.employees = employees;
    }

    @Override
    public List<Arrangement> apply(List<Arrangement> arrangements, Random random) {

        arrangements.forEach(arrangement -> {
            for(int i = 0; i < numberOfShiftsToChange; i++) {
                if (random.nextDouble() < this.probability) {
                    arrangement.getShifts().get(
                            random.nextInt(arrangement.size())
                    ).setEmployee(this.employees.get(random.nextInt(employees.size())));
                }
            }
        });

        return arrangements;
    }
}
