package Crossovers;

import Arrangement.Arrangement;
import Model.Shift;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicCrossover extends AbstractCrossover<Arrangement> {

    public BasicCrossover(int crossoverPoints) {
        super(crossoverPoints);
    }

    @Override
    protected List<Arrangement> mate(Arrangement parent1, Arrangement parent2, int cuttingPoints, Random random) {
        List<Arrangement> arrangements = new ArrayList<>();
        List<Shift> child1Shifts = new ArrayList<>();
        List<Shift> child2Shifts = new ArrayList<>();
        int shiftsCount = parent1.getShifts().size();
        boolean straightAssign = true;

        for(int i=0; i < shiftsCount; i++) {
            if (straightAssign) {
                child1Shifts.add(parent1.getShifts().get(i));
                child2Shifts.add(parent2.getShifts().get(i));
            } else {
                child1Shifts.add(parent2.getShifts().get(i));
                child2Shifts.add(parent1.getShifts().get(i));
            }

            if (i % (shiftsCount / cuttingPoints) == 0) {
                straightAssign = !straightAssign;
            }
        }

        arrangements.add(new Arrangement(child1Shifts));
        arrangements.add(new Arrangement(child2Shifts));
        return arrangements;
    }
}
