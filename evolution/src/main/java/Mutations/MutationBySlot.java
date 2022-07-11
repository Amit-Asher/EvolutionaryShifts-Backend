package Mutations;

import Arrangement.Arrangement;
import Model.Shift;
import Model.Slot.ReqSlot;
import Model.Slot.Slot;
import Rule.RuleSlots.RuleSlotsPreference;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MutationBySlot implements EvolutionaryOperator<Arrangement> {

    private final double probability;
    private final int numberOfShiftsToChange;
    private final List<RuleSlotsPreference> employeesPreferences;
    private final List<ReqSlot> reqSlots;

    public MutationBySlot(double probability, int numberOfShiftsToChange, List<RuleSlotsPreference> employeesPreferences, List<ReqSlot> reqSlots) {
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

    @Override
    public List<Arrangement> apply(List<Arrangement> arrangements, Random random) {
        arrangements.forEach(arrangement -> {
            for(int i = 0; i < numberOfShiftsToChange; i++) {
                Shift shiftToMutate = arrangement.getShifts().get(random.nextInt(arrangement.size()));
                if (random.nextDouble() < this.probability && !isGoodShift(shiftToMutate)) {
                    Slot newSlot = this.reqSlots.get(random.nextInt(reqSlots.size())).getSlot();
                    shiftToMutate.setSlot(newSlot);
                }
            }
        });

        return arrangements;
    }
}
