package EvolutionaryShifts.Crossovers;

import EvolutionaryShifts.Arrangement.Arrangement;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.List;
import java.util.Random;

public class ArrangementCrossover extends AbstractCrossover<Arrangement> {
    public ArrangementCrossover(int crossoverPoints) {
        super(crossoverPoints);
    }

    //i--numberOfCrossoverPoints
    //two evolved offspring
    @Override
    protected List<Arrangement> mate(Arrangement arrangeShifts, Arrangement t1, int i, Random random) {
        return null;
    }
}
