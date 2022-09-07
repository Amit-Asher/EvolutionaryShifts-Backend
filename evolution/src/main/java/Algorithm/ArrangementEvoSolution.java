package Algorithm;


import Arrangement.Arrangement;

public class ArrangementEvoSolution extends ArrangementSolution {
    public int generationNumber;
    public long elapsedTime;

    public ArrangementEvoSolution(Arrangement arrangement,
                                  int generationNumber,
                                  double fitness,
                                  long elapsedTime) {
        super(arrangement, fitness);
        this.generationNumber = generationNumber;
        this.elapsedTime = elapsedTime;
    }
}