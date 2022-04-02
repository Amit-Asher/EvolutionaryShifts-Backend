package EvolutionaryShifts.Rule;

import EvolutionaryShifts.Arrangement.Arrangement;

public interface IRule<T>
{
    /* EVALUATE RULE FITNESS ON A GIVEN ARRANGEMENT */
    double evaluate(Arrangement arrangement);
    void addPreference(T preference);

    String getName();
}