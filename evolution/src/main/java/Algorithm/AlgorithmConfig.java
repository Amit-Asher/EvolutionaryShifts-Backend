package Algorithm;


import Arrangement.Arrangement;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;

public class AlgorithmConfig
{
    // todo: maybe we should remove rules weights from set props stage and define them here onRun

    private final List<EvolutionaryOperator<Arrangement>> mutations;
    private final AbstractCrossover<Arrangement> crossover;
    private final SelectionStrategy<? super Arrangement> selectionStrategy;
    private final List<TerminationCondition> terminationConditions;
    private final int populationSize;
    private final int elitism;

    public AlgorithmConfig() {

    }

    public ArrayList<TerminationCondition> getTerminationConditions() {
        return terminationConditions;
    }

    public void setTerminationConditions(ArrayList<TerminationCondition> terminationConditions) {
        this.terminationConditions = terminationConditions;
    }

    public int getPopulationSize() {
        return populationSize;
    }
}
