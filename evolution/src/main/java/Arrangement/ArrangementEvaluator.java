package Arrangement;

import Rule.IRule;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;
import java.util.Map;

public class ArrangementEvaluator implements FitnessEvaluator<Arrangement>
{
    protected Map<IRule, Double> m_Rule2Weight;

    public ArrangementEvaluator(Map<IRule, Double> rule2Weight) throws Exception
    {
        if (rule2Weight != null && !rule2Weight.isEmpty()) {
            if(rule2Weight.values().stream().mapToDouble(d -> d).sum() != 1)
                     throw new Exception("go away");
        }

        m_Rule2Weight = rule2Weight;
    }

    @Override
    public double getFitness(Arrangement arrangement,
                             List<? extends Arrangement> list)
    {
        double fitness = 0;
        for (IRule rule : m_Rule2Weight.keySet())
        {
            fitness += rule.evaluate(arrangement) * m_Rule2Weight.get(rule);
        }

        return fitness;
    }

    @Override
    public boolean isNatural() {
        //100 == best fitness
        return true;
    }
}
