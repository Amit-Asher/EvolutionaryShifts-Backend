package com.evo.springboot.app.DTO.Outgoing;

import java.util.List;

public class EvolutionStatusDTO {
    private List<ShiftDTO> arrangement;
    private int generationNumber;
    private double fitness;
    private boolean isFinished;

    public EvolutionStatusDTO(List<ShiftDTO> arrangement, int generationNumber, double fitness, boolean isFinished) {
        this.arrangement = arrangement;
        this.generationNumber = generationNumber;
        this.fitness = fitness;
        this.isFinished = isFinished;
    }

    public List<ShiftDTO> getArrangement() {
        return arrangement;
    }

    public void setArrangement(List<ShiftDTO> arrangement) {
        this.arrangement = arrangement;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    public void setGenerationNumber(int generationNumber) {
        this.generationNumber = generationNumber;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
