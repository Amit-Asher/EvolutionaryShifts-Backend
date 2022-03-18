package EvolutionaryShifts;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;
import java.util.Map;

public class ArrangementEvaluator implements FitnessEvaluator<Arrangement>
{
    protected Map<Rule, Double> m_Rule2Weight;

    public ArrangementEvaluator(Map<Rule, Double> rule2Weight) throws Exception
    {
        if (rule2Weight != null && !rule2Weight.isEmpty()) {
            if(rule2Weight.values().stream().mapToDouble(d -> d).sum() != 1)
                     throw new Exception("go away");
        }

        m_Rule2Weight = rule2Weight;
    }

    @Override
    public double getFitness(Arrangement arrangeShifts,
                             List<? extends Arrangement> list)
    {
        double fitness = 0;
        for (Rule rule:m_Rule2Weight.keySet())
        {
            fitness += rule.Execute(arrangeShifts) * m_Rule2Weight.get(rule);
        }

        return fitness;
    }

    @Override
    public boolean isNatural() { return true; }//100 == best fitness
}
