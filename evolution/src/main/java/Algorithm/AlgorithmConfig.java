package Algorithm;


import Arrangement.Arrangement;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmConfig
{
    // todo: maybe we should remove rules weights from set props stage and define them here onRun

    private final List<EvolutionaryOperator<Arrangement>> mutations;
    private final AbstractCrossover<Arrangement> crossover;
    private final SelectionStrategy<? super Arrangement> selectionStrategy;
    private final List<TerminationCondition> terminationConditions;
    private final int populationSize;
    private final int elitism;

    public AlgorithmConfig(List<EvolutionaryOperator<Arrangement>> mutations,
                           AbstractCrossover<Arrangement> crossover,
                           SelectionStrategy<? super Arrangement> selectionStrategy,
                           List<TerminationCondition> terminationConditions,
                           int populationSize,
                           int elitism) {
        this.mutations = mutations;
        this.crossover = crossover;
        this.selectionStrategy = selectionStrategy;
        this.terminationConditions = terminationConditions;
        this.populationSize = populationSize;
        this.elitism = elitism;
    }

    public List<EvolutionaryOperator<Arrangement>> getMutations() {
        return mutations;
    }

    public AbstractCrossover<Arrangement> getCrossover() {
        return crossover;
    }

    public SelectionStrategy<? super Arrangement> getSelectionStrategy() {
        return selectionStrategy;
    }

    public List<TerminationCondition> getTerminationConditions() {
        return terminationConditions;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getElitism() {
        return elitism;
    }
}
