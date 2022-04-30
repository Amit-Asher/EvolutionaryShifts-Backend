package Algorithm;


import Arrangement.Arrangement;

public class ArrangementEvoSolution extends ArrangementSolution {
    public int generationNumber;

    public ArrangementEvoSolution(Arrangement arrangement,
                                  int generationNumber,
                                  double fitness) {
        super(arrangement, fitness);
        this.generationNumber = generationNumber;
    }
}