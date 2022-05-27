package Mutations;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.PrfSlot;
import Model.Slot.ReqSlot;
import Model.Slot.Slot;
import Rule.RuleSlots.RuleSlotsPreference;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class MutationSwapEmployees implements EvolutionaryOperator<Arrangement> {

    private final double probability;
    private final int numberOfShiftsToChange;
    private final List<RuleSlotsPreference> employeesPreferences;
    private final List<ReqSlot> reqSlots;

    public MutationSwapEmployees(double probability, int numberOfShiftsToChange, List<RuleSlotsPreference> employeesPreferences, List<ReqSlot> reqSlots) {
        this.probability = probability;
        this.numberOfShiftsToChange = numberOfShiftsToChange;
        this.employeesPreferences = employeesPreferences;
        this.reqSlots = reqSlots;
    }

    private boolean isGoodShift(Shift shift) {
        RuleSlotsPreference employeePreferences = this.employeesPreferences.stream()
                .filter(x -> x.getEmployee().equals(shift.getEmployee()))
                .findFirst()
                .orElse(null);

        if (employeePreferences == null) {
            return true;
        }

        AtomicBoolean isGoodShift = new AtomicBoolean(false);
        employeePreferences.getSlots().forEach(prfSlot -> {
            if(!isGoodShift.get()) {
                isGoodShift.set(prfSlot.getSlot().equals(shift.getSlot()));
            }
        });

        return isGoodShift.get();
    }

    private List<Slot> getEmployeePreferredSlots(Employee employee) {
        RuleSlotsPreference employeePreferences = this.employeesPreferences.stream()
                .filter(x -> x.getEmployee().equals(employee))
                .findFirst()
                .orElse(null);

        if (employeePreferences == null) {
            return new ArrayList<>();
        }

        return employeePreferences.getSlots().stream().map(PrfSlot::getSlot).collect(Collectors.toList());
    }

    @Override
    public List<Arrangement> apply(List<Arrangement> arrangements, Random random) {
        arrangements.forEach(arrangement -> {
            for(int i = 0; i < numberOfShiftsToChange; i++) {
                Shift shiftToMutate1 = arrangement.getShifts().get(random.nextInt(arrangement.size()));
                Shift shiftToMutate2 = arrangement.getShifts().get(random.nextInt(arrangement.size()));
                if (random.nextDouble() < this.probability &&
                        !isGoodShift(shiftToMutate1) &&
                        !isGoodShift(shiftToMutate2)) {
                    Employee employeeTmp = shiftToMutate1.getEmployee();
                    shiftToMutate1.setEmployee(shiftToMutate2.getEmployee());
                    shiftToMutate2.setEmployee(employeeTmp);
                }
            }
        });

        return arrangements;
    }
}
