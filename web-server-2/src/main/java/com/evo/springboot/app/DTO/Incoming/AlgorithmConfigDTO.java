package com.evo.springboot.app.DTO.Incoming;

import java.util.List;

public class AlgorithmConfigDTO {
    private List<EvolutionaryOperatorDTO> mutations;
    private EvolutionaryOperatorDTO crossover;
    private EvolutionaryOperatorDTO selection;
    private List<EvolutionaryOperatorDTO> terminationCondition;
    private Integer populationSize;
    private Integer elitism;

    public List<EvolutionaryOperatorDTO> getMutations() {
        return mutations;
    }

    public void setMutations(List<EvolutionaryOperatorDTO> mutations) {
        this.mutations = mutations;
    }

    public EvolutionaryOperatorDTO getCrossover() {
        return crossover;
    }

    public void setCrossover(EvolutionaryOperatorDTO crossover) {
        this.crossover = crossover;
    }

    public EvolutionaryOperatorDTO getSelection() {
        return selection;
    }

    public void setSelection(EvolutionaryOperatorDTO selection) {
        this.selection = selection;
    }

    public List<EvolutionaryOperatorDTO> getTerminationCondition() {
        return terminationCondition;
    }

    public void setTerminationCondition(List<EvolutionaryOperatorDTO> terminationCondition) {
        this.terminationCondition = terminationCondition;
    }

    public Integer getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(Integer populationSize) {
        this.populationSize = populationSize;
    }

    public Integer getElitism() {
        return elitism;
    }

    public void setElitism(Integer elitism) {
        this.elitism = elitism;
    }
}
