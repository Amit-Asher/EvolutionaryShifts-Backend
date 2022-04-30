package Algorithm;

public class EvolutionStatus {
    public ArrangementEvoSolution arrangementSolution;
    public boolean isFinished;

    public EvolutionStatus(ArrangementSolution arrangementSolution, boolean isFinished) {

        this.arrangementSolution = (ArrangementEvoSolution) arrangementSolution;
        this.isFinished = isFinished;
    }
}
