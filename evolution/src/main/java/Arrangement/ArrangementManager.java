package Arrangement;

import Algorithm.AlgorithmConfig;
import Crossovers.ArrangementCrossover;
import Model.Employee.EmployeePreferences;
import Mutations.MutationByDay;
import Rule.IRule;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArrangementManager
{
    protected ArrangementProperties m_CurrArrangementProp;
    protected ArrangementStatus m_CurrArrangementStatus;
    protected EvolutionEngine<Arrangement> engine;

    public ArrangementStatus getCurrArrangementStatus() {
        return m_CurrArrangementStatus;
    }

    public void updateEmployeePreference(EmployeePreferences employeePreference)
    {
        if (m_CurrArrangementStatus != ArrangementStatus.WAIT_EMP_REQ)
            throw new RuntimeException("Failed to setEmployeePreference\n Current status: " +
                    m_CurrArrangementStatus + " expected: WAIT_EMP_REQ");

        // add employees preferences to each rule configuration
        employeePreference.getPreferences().forEach((ruleNameInput, preferenceInput) -> {
            m_CurrArrangementProp.m_rule2weight.keySet().stream().filter(rule -> {
                return rule.getName().equals(ruleNameInput);
            }).forEach(rule -> rule.addPreference(preferenceInput));
        });
    }

    public void setCurrArrangementProp(ArrangementProperties m_CurrArrangementProp) {
        this.m_CurrArrangementProp = m_CurrArrangementProp;
        this.m_CurrArrangementStatus = ArrangementStatus.WAIT_EMP_REQ;
    }

    public void BlockEmployeesToSetPref()
    {
        if (this.m_CurrArrangementStatus != ArrangementStatus.WAIT_EMP_REQ)
            return;

        this.m_CurrArrangementStatus = ArrangementStatus.SOLVING;
    }

    public void setCurrArrangementStatus(ArrangementStatus m_CurrArrangementStatus) {
        this.m_CurrArrangementStatus = m_CurrArrangementStatus;
    }

    // todo: wrap with thread
    public Arrangement startAlgorithm(AlgorithmConfig algorithmConfig)
    {
        Map<IRule, Double> rule2Weight = m_CurrArrangementProp.getM_rule2weight();
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

        return engine.evolve(100, // 100 individuals in the population.
                5, // 5% elitism.
                new TargetFitness(0, true));

    /*
    * todo:
    *
    *
    *
    * create EvolutionThread extends Thread that
    * implements all the logic off creating new engine
    * and run evolve based on algorithmConfig.
    *
    *
    *  */
    }

}
