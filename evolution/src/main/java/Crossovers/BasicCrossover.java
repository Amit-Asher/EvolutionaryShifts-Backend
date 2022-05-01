package Crossovers;

import Arrangement.Arrangement;
import Model.Shift;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BasicCrossover extends AbstractCrossover<Arrangement> {
    public BasicCrossover(int crossoverPoints) {
        super(crossoverPoints);
    }

    @Override
    protected List<Arrangement> mate(Arrangement parent1,
                                     Arrangement parent2,
                                     int numberOfCrossoverPoints,
                                     Random random)
    {
        //Assume the two parents in the same size

        boolean isParent1 = random.nextBoolean();
        ArrayList<Integer> crossoverPoints = new ArrayList<>(numberOfCrossoverPoints);
        List<Arrangement> children = new ArrayList<>(2);
        Arrangement child1 = new Arrangement();
        Arrangement child2 = new Arrangement();

        for (int i = 0;i < numberOfCrossoverPoints;i++)
            crossoverPoints.add(random.nextInt(parent1.getShifts().size()));
        Collections.sort(crossoverPoints);

        for (int indexCurr = 0;indexCurr < numberOfCrossoverPoints;indexCurr++)
        {
           int from = indexCurr == 0 ? 0 :  crossoverPoints.get(indexCurr - 1);
           int to = crossoverPoints.get(indexCurr);

            if(isParent1)
            {
                for (int i = from;i < to;i++)
                {
                    child1.addShift(new Shift(parent1.getShifts().get(i)));
                    child2.addShift(new Shift(parent2.getShifts().get(i)));
                }
            }
            else
            {
                for (int i = from;i < to;i++)
                {
                    child2.addShift(new Shift(parent1.getShifts().get(i)));
                    child1.addShift(new Shift(parent2.getShifts().get(i)));
                }
            }

            isParent1 = !isParent1;
        }

        children.add(child1);
        children.add(child2);

        return children;
    }
}
