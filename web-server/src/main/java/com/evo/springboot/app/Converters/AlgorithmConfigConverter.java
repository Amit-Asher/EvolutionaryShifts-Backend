package com.evo.springboot.app.Converters;

import Algorithm.AlgorithmConfig;
import Arrangement.Arrangement;
import Crossovers.CrossoverFactory;
import Selections.SelectionFactory;
import TermConds.TermCondFactory;
import com.evo.springboot.app.DTO.Incoming.AlgorithmConfigDTO;
import com.evo.springboot.app.ToRefactor.MutationFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmConfigConverter {

    @Autowired
    MutationFactory mutationFactory;

    public AlgorithmConfig convert(AlgorithmConfigDTO algorithmConfigDTO) throws JSONException {

        int mutationsCount = algorithmConfigDTO.getMutations().size();
        List<EvolutionaryOperator<Arrangement>> mutations = new ArrayList<>(mutationsCount);
        algorithmConfigDTO.getMutations().forEach(mutation -> {
            try {
                mutations.add(mutationFactory.createMutation(
                        mutation.getType(),
                        mutation.getParams()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        AbstractCrossover<Arrangement> crossover = CrossoverFactory.createCrossover(
                algorithmConfigDTO.getCrossover().getType(),
                algorithmConfigDTO.getCrossover().getParams()
        );

        SelectionStrategy<? super Arrangement> selection = SelectionFactory.createSelection(
                algorithmConfigDTO.getSelection().getType(),
                algorithmConfigDTO.getSelection().getParams()
        );

        List<TerminationCondition> terminationConditions = new ArrayList<>();
        algorithmConfigDTO.getTerminationCondition().forEach(termCond -> {
            try {
                terminationConditions.add(TermCondFactory.createTerminationCondition(
                        termCond.getType(),
                        termCond.getParams()
                ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        int populationSize = algorithmConfigDTO.getPopulationSize();
        int elitism = algorithmConfigDTO.getElitism();

        AlgorithmConfig algorithmConfig = new AlgorithmConfig(
                mutations,
                crossover,
                selection,
                terminationConditions,
                populationSize,
                elitism
        );

        return algorithmConfig;
    }
}
