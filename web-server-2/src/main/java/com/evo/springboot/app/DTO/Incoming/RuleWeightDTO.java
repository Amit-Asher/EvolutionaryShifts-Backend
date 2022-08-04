package com.evo.springboot.app.DTO.Incoming;

public class RuleWeightDTO {
    private String ruleName;
    private double weight;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
