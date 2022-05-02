package Mutations;

import Arrangement.Arrangement;
import Model.Shift;
import Mutations.MutateBy.MutateBy;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.*;

public class BasicMutation<T> implements EvolutionaryOperator<Arrangement> {

    private double m_Probability;
    private ArrayList<T> objects;
    private MutateBy<T> mutateBy;
    private int howMuchToMutate;

    public BasicMutation(double probability,
                         ArrayList<T> objects,
                         MutateBy<T> mutateBy,
                         int howMuchToMutate)
    {
        if(objects.size() < 1)
            throw new IllegalArgumentException("BasicMutation: can't use with <= 1 T objects");

        if(howMuchToMutate <= 0)
            throw new IllegalArgumentException("BasicMutation: howMuchToMutate is <= 0");

        if(probability < 0 || probability > 1)
            throw new IllegalArgumentException("BasicMutation: probability isn't between 0.0 to 1.0");

        m_Probability = probability;
        this.objects = new ArrayList<>();
        this.objects.addAll(objects);
        this.howMuchToMutate = howMuchToMutate;
        this.mutateBy = mutateBy;
    }

    public BasicMutation(double probability,
                         ArrayList<T> objects,
                         MutateBy<T> mutateBy)
    {
        new BasicMutation(probability, objects, mutateBy, 1);
    }



    @Override
    public List<Arrangement> apply(List<Arrangement> list, Random random) {
        for(int i = 0;i < list.size();i++)
        {
            if(howMuchToMutate > list.get(i).size())
                throw new IllegalArgumentException("BasicMutation: howMuchToMutate is bigger this size of arrangement");

            if(random.nextDouble() <= m_Probability)
            {
                ArrayList<Integer> indexToMutate = indexToMutate(
                        list.get(i), random);

                for (int j = 0;j < howMuchToMutate;j ++)
                    mutateMe(list.get(i).getShifts().get(indexToMutate.get(j)), random);
            }
        }

        return list;
    }

    private ArrayList<Integer> indexToMutate(Arrangement arrangement,
                                             Random random)
    {
        ArrayList<Integer> indexToMutate = new ArrayList<>(howMuchToMutate);
        ArrayList<Integer> indexes = new ArrayList<>(arrangement.size());

        for (int j = 0;j < arrangement.size();j ++)
            indexes.add(j);

        for (int j = 0;j < howMuchToMutate;j ++)
        {
            int randIndex = random.nextInt(indexes.size());
            indexToMutate.add(indexes.get(randIndex));
            indexes.remove(randIndex);
        }

        return indexToMutate;
    }

    private void mutateMe(Shift shift, Random random)
    {
        ArrayList<T> tempObjects = new ArrayList<>(objects);
        T oldObject =  this.mutateBy.get(shift);

        for (int j = 0;j < tempObjects.size();j++)
            if(tempObjects.get(j).equals(oldObject)) {
                tempObjects.remove(j);
                break;
            }

        int indexObject = random.nextInt(tempObjects.size());
        T newObject = tempObjects.get(indexObject);
        this.mutateBy.set(shift, newObject);
    }
}
