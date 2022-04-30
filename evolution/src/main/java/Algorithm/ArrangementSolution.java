package Algorithm;

import Arrangement.Arrangement;

public class ArrangementSolution {
    public Arrangement arrangement;
    public double fitness;

    public ArrangementSolution(Arrangement arrangement,
                           double fitness) {
        this.arrangement = new Arrangement(arrangement); // deep clone
        this.fitness = fitness;
    }
}
