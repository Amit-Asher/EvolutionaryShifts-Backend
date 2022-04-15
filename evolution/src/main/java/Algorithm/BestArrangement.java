package Algorithm;


import Arrangement.Arrangement;

public class BestArrangement {
    public Arrangement arrangement;
    public int generationNumber;
    public double fitness;

    public BestArrangement(Arrangement arrangement, int generationNumber, double fitness) {
        this.arrangement = new Arrangement(arrangement); // deep clone
        this.generationNumber = generationNumber;
        this.fitness = fitness;
    }
}