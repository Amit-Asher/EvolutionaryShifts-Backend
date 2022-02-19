import org.uncommons.maths.random.DiscreteUniformGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.PoissonGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.factories.StringFactory;
import org.uncommons.watchmaker.framework.operators.StringCrossover;
import org.uncommons.watchmaker.framework.operators.StringMutation;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;

import java.util.ArrayList;
import java.util.List;

public final class StringsExample
{
    public static void main(String[] args)
    {
        System.out.println("test");
//        char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ' };
//        String target = "Hello World";
//        ArrangementFactory factory = new ArrangementFactory(ALPHABET, target.length());
//        List<EvolutionaryOperator<String>> operators = new ArrayList<EvolutionaryOperator<String>>();

//        operators.add(new ArrangementCrossover());
//        operators.add(new ArrangementMutation());

        //// mutation impl operator
        //// crossover inherit from abstract crossover which impl operator
//        operators.add(new StringMutation(ALPHABET, new Probability(0.02d)));
//        operators.add(new StringCrossover());

//        EvolutionEngine<String> engine = new GenerationalEvolutionEngine<String>(factory,
//                pipeline,
//                new org.uncommons.watchmaker.examples.strings.StringEvaluator(target),
//                new RouletteWheelSelection(),
//                new MersenneTwisterRNG());
    }
}