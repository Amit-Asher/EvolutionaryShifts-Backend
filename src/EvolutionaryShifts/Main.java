package EvolutionaryShifts;

import EvolutionaryShifts.Arrangement.Arrangement;
import EvolutionaryShifts.Arrangement.ArrangementEvaluator;
import EvolutionaryShifts.Arrangement.ArrangementFactory;
import EvolutionaryShifts.Crossovers.ArrangementCrossover;
import EvolutionaryShifts.Mutations.MutationByDay;
import EvolutionaryShifts.Rule.Rule;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        Arrangement solution = evolveArrangeShifts();
    }

    public static Arrangement evolveArrangeShifts()
    {
        //we need to get from user: (we can set default values
        //So that they do not get lost)
        //Pipeline must contain at least one operator
        //list of mutation
        //list of crossover
        //selection
        //num of population
        //elitism
        //list of TerminationCondition

        //data of employees
        //name and password for the work place
        //list of rules
        //data for the work place

        Map<Rule, Double> rule2Weight = new HashMap<Rule, Double>();
        ArrangementFactory factory = new ArrangementFactory();
        List<EvolutionaryOperator<Arrangement>> operators = new ArrayList<>(2);
        operators.add(new MutationByDay(0.3));
        operators.add(new ArrangementCrossover(2));
        EvolutionaryOperator<Arrangement> pipeline = new EvolutionPipeline<>(operators);
        EvolutionEngine<Arrangement> engine = null;
        try {
            engine = new GenerationalEvolutionEngine<>(
                    factory,
                    pipeline,
                    new ArrangementEvaluator(rule2Weight),
                    new RouletteWheelSelection(),
                    new MersenneTwisterRNG()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        engine.addEvolutionObserver(new EvolutionLogger());//need to remove after

        return engine.evolve(100, // 100 individuals in the population.
                5, // 5% elitism.
                new TargetFitness(0, true));
        //targetFitness - The fitness score that must be achieved by
        //at least
        //one individual in the population in order
        // for this condition to be satisfied.
        //TargetFitness implements TerminationCondition
        //לא חייב להשתמש דווקא בו אפשר לדוגמא ב
        //GenerationCount implements TerminationCondition
        //אפשר אולי לתת למשתמש להחליט מה לבחור אבל
        //לא צריך לתת חופש לגמרי כי הוא סתם דוגמא יכול לבחור
        //את TargetFitness עם המספר 100 ולבהות במסך עד שימות
    }

    private static class EvolutionLogger implements EvolutionObserver<Arrangement>
    {
        public void populationUpdate(PopulationData<? extends Arrangement> data)
        {
            System.out.printf("Generation %d: %s\n", data.getGenerationNumber(), data.getBestCandidate());
        }
    }
}
