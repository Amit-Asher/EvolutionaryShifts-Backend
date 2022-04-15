package Algorithm;

public class EvolutionStatus {
    public BestArrangement bestArrangement;
    public boolean isFinished;

    public EvolutionStatus(BestArrangement bestArrangement, boolean isFinished) {
        this.bestArrangement = bestArrangement;
        this.isFinished = isFinished;
    }
}
