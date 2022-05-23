package Algorithm;


import Arrangement.Arrangement;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.TerminationCondition;

public class AlgorithmConfig
{
    // todo: maybe we should remove rules weights from set props stage and define them here onRun

    private final EvolutionaryOperator<Arrangement> pipeline; // mutations and crossovers here
    private final SelectionStrategy<? super Arrangement> selectionStrategy;
    private final TerminationCondition terminationCondition;
    private final int populationSize;
    private final int elitism;

    public AlgorithmConfig(EvolutionaryOperator<Arrangement> pipeline,
                           SelectionStrategy<? super Arrangement> selectionStrategy,
                           TerminationCondition terminationCondition,
                           int populationSize,
                           int elitism) {
        this.pipeline = pipeline;
        this.selectionStrategy = selectionStrategy;
        this.terminationCondition = terminationCondition;
        this.populationSize = populationSize;
        this.elitism = elitism;
    }

    public EvolutionaryOperator<Arrangement> getPipeline() {
        return pipeline;
    }

    public SelectionStrategy<? super Arrangement> getSelectionStrategy() {
        return selectionStrategy;
    }

    public TerminationCondition getTerminationCondition() {
        return terminationCondition;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getElitism() {
        return elitism;
    }
}
