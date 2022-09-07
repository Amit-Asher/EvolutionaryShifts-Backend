package com.evo.springboot.app.DTO.Outgoing;

import java.util.List;

public class EvolutionStatusDTO {
    private List<ShiftDTO> arrangement;
    private int generationNumber;
    private double fitness;
    private boolean isFinished;
    private long elapsedTime;

    public EvolutionStatusDTO(List<ShiftDTO> arrangement,
                              int generationNumber,
                              double fitness,
                              boolean isFinished,
                              long elapsedTime)
    {
        this.arrangement = arrangement;
        this.generationNumber = generationNumber;
        this.fitness = fitness;
        this.isFinished = isFinished;
        this.elapsedTime = elapsedTime;
    }

    public List<ShiftDTO> getArrangement() {
        return arrangement;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
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
