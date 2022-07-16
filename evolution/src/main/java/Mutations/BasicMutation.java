package Mutations;

import Arrangement.Arrangement;
import Model.Shift;
import Mutations.MutateBy.MutateBy;
import Utils.OneTimeRandomRange;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.*;

public class BasicMutation<T> implements EvolutionaryOperator<Arrangement> {

    private double m_Probability;
    private List<T> options;
    private MutateBy<T> mutateBy;
    private int howMuchToMutate;

    public BasicMutation(double probability,
                         List<T> options,
                         MutateBy<T> mutateBy,
                         int howMuchToMutate)
    {
        if(options.size() <= 1)
            throw new IllegalArgumentException("BasicMutation: can't use with <= 1 T objects");

        if(howMuchToMutate <= 0)
            throw new IllegalArgumentException("BasicMutation: howMuchToMutate is <= 0");

        if(probability < 0 || probability > 1)
            throw new IllegalArgumentException("BasicMutation: probability isn't between 0.0 to 1.0");

        m_Probability = probability;
        this.options = new ArrayList<>();
        this.options.addAll(options);
        this.howMuchToMutate = howMuchToMutate;
        this.mutateBy = mutateBy;
    }

    public BasicMutation(double probability,
                         List<T> options,
                         MutateBy<T> mutateBy)
    {
        this(probability, options, mutateBy, 1);
    }



    @Override
    public List<Arrangement> apply(List<Arrangement> list, Random random) {
        for (Arrangement arrangement : list) {
            int fixedHowMuchToMutate = howMuchToMutate;

            if (howMuchToMutate > arrangement.size())
                fixedHowMuchToMutate = arrangement.size();

            if (random.nextDouble() <= m_Probability)
            {
                ArrayList<Integer> indexToMutate = OneTimeRandomRange.random(howMuchToMutate,
                        arrangement.size(), random, true);

                for (int j = 0; j < fixedHowMuchToMutate; j++)
                    mutateMe(arrangement.getShifts().get(indexToMutate.get(j)), random);
            }
        }

        return list;
    }

    private void mutateMe(Shift shift, Random random)
    {
        ArrayList<T> tempOptions = new ArrayList<>(options);
        T oldObject =  this.mutateBy.get(shift);

        for (int j = 0;j < tempOptions.size();j++)
            if(tempOptions.get(j).equals(oldObject)) {
                tempOptions.remove(j);
                break;
            }

        int indexObject = random.nextInt(tempOptions.size());
        T newObject = tempOptions.get(indexObject);
        this.mutateBy.set(shift, newObject);
    }
}
