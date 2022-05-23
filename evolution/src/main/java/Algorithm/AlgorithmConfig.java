package Algorithm;


import Arrangement.Arrangement;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;

public class AlgorithmConfig
{
    private ArrayList<TerminationCondition> terminationConditions;
    int populationSize;
    int elitism;
    private ArrayList<EvolutionaryOperator<Arrangement>> mutation;



    private AbstractCrossover<Arrangement> crossover;
    private SelectionStrategy<Object> selection;


    public ArrayList<TerminationCondition> getTerminationConditions() {
        return terminationConditions;
    }

    public void setTerminationConditions(ArrayList<TerminationCondition> terminationConditions) {
        this.terminationConditions = terminationConditions;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getElitism() {
        return elitism;
    }

    public void setElitism(int elitism) {
        this.elitism = elitism;
    }

    public ArrayList<EvolutionaryOperator<Arrangement>> getMutation() {
        return mutation;
    }

    public void setMutation(ArrayList<EvolutionaryOperator<Arrangement>> mutation) {
        this.mutation = mutation;
    }

    public AbstractCrossover<Arrangement> getCrossover() {
        return crossover;
    }

    public void setCrossover(AbstractCrossover<Arrangement> crossover) {
        this.crossover = crossover;
    }

    public SelectionStrategy<Object> getSelection() {
        return selection;
    }

    public void setSelection(SelectionStrategy<Object> selection) {
        this.selection = selection;
    }
}
