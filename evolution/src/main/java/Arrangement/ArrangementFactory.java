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
        int arrangementSize = 0;
        for (ReqSlot reqSlot : reqSlots) {
            arrangementSize += reqSlot.getPersonnelSize().getHigh();
        }

        for (int i = 0; i < arrangementSize; i++) {
            Employee chosenEmployee = activeEmployees.get(random.nextInt(activeEmployees.size()));
            ReqSlot chosenReqSlot = reqSlots.get(random.nextInt(reqSlots.size()));
            shifts.add(new Shift(chosenEmployee, chosenReqSlot.getRole(), chosenReqSlot.getSlot()));
        }

        return new Arrangement(shifts);
    }
}
