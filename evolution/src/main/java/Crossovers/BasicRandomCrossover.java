package Crossovers;

import Arrangement.Arrangement;
import Model.Shift;
import Utils.OneTimeRandomRange;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BasicRandomCrossover extends AbstractCrossover<Arrangement>
{
    private Arrangement parent1;
    private Arrangement parent2;

    public BasicRandomCrossover(int crossoverPoints)
    {
        super(crossoverPoints);
    }

    @Override
    protected List<Arrangement> mate(Arrangement parent1,
                                     Arrangement parent2,
                                     int numberOfCrossoverPoints,
                                     Random random)
    {
        this.parent1 = parent1;
        this.parent2 = parent2;

        if (this.parent1.size() != this.parent2.size())
        {
            throw new IllegalArgumentException("Cannot perform cross-over with different length parents.");
        }

        boolean isParent1 = random.nextBoolean();
        List<Arrangement> children = new ArrayList<>(2);
        Arrangement child1 = new Arrangement();//offsprings
        Arrangement child2 = new Arrangement();
        ArrayList<Integer> crossoverPoints = OneTimeRandomRange.random(numberOfCrossoverPoints,
                this.parent1.size(), random, false);

        Collections.sort(crossoverPoints);
        for (int indexCurr = 0;indexCurr < numberOfCrossoverPoints;indexCurr++)
        {
            crossMate(isParent1,
                    indexCurr == 0 ? 0 :  crossoverPoints.get(indexCurr - 1),
                    crossoverPoints.get(indexCurr),
                    child1, child2);

            isParent1 = !isParent1;
        }

        crossMate(isParent1,
                crossoverPoints.get(crossoverPoints.size() - 1),
                this.parent1.size(),
                child1, child2);

        children.add(child1);
        children.add(child2);

        return children;
    }

    private void crossMate(boolean isParent1,
                           int from,
                           int to,
                           Arrangement child1,
                           Arrangement child2)
    {
        if(isParent1)
        {
            for (int i = from;i < to;i++)
            {
                child1.addShift(new Shift(this.parent1.getShifts().get(i)));
                child2.addShift(new Shift(this.parent2.getShifts().get(i)));
            }
        }
        else
        {
            for (int i = from;i < to;i++)
            {
                child2.addShift(new Shift(this.parent1.getShifts().get(i)));
                child1.addShift(new Shift(this.parent2.getShifts().get(i)));
            }
        }
    }
}