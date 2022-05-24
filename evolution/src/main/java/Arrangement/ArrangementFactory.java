package Arrangement;

import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.ReqSlot;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrangementFactory extends AbstractCandidateFactory<Arrangement> {

    private List<ReqSlot> reqSlots;
    private List<Employee> activeEmployees;

    public ArrangementFactory(List<ReqSlot> reqSlots, List<Employee> activeEmployees) {
        this.reqSlots = reqSlots;
        this.activeEmployees = activeEmployees;
    }

    @Override
    public Arrangement generateRandomCandidate(Random random) {
        List<Shift> shifts = new ArrayList<>();

        reqSlots.forEach(reqSlot -> {
            for (int i=0; i < reqSlot.getPersonnelSize().getHigh(); i++) {
                Employee chosenEmployee = activeEmployees.get(random.nextInt(activeEmployees.size()));
                Shift shift = new Shift(chosenEmployee, reqSlot.getRole(), reqSlot.getSlot());
                shifts.add(shift);
            }
        });

        return new Arrangement(shifts);
    }
}
