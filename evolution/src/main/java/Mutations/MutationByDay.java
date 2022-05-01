package Mutations;

import Arrangement.Arrangement;
import Model.Day;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.*;

public class MutationByDay implements EvolutionaryOperator<Arrangement> {

    private double m_Probability;
    //private ArrayList<ReqSlot> reqSlotsProp;
    private Set<Day> days;
    private int howMuchToMutate;

    public MutationByDay(double probability,
                         Set<Day> days,
                         int howMuchToMutate)
    {
        if(days.size() < 1)
            throw new IllegalArgumentException("MutationByDay: can't use with <= 1 work days");

        if(howMuchToMutate <= 0)
            throw new IllegalArgumentException("MutationByDay: howMuchToMutate is <= 0");

        if(probability < 0 || probability > 1)
            throw new IllegalArgumentException("MutationByDay: probability isn't between 0.0 to 1.0");

        m_Probability = probability;
        this.days = new HashSet(days);
        this.howMuchToMutate = howMuchToMutate;
    }

    public MutationByDay(double probability,
                         Set<Day> days)
    {
        new MutationByDay(probability, days, 1);
    }

    @Override
    public List<Arrangement> apply(List<Arrangement> list, Random random) {
        for(int i = 0;i < list.size();i++)
        {
            if(howMuchToMutate > list.get(i).size())
                throw new IllegalArgumentException("MutationByDay: howMuchToMutate is bigger this size of arrangement");

            if(random.nextDouble() <= m_Probability)
            {
                /////////////////
                /*
                for(int j = 0;j < reqSlotsProp.size();j++)
                {
                    if(reqSlotsProp.get(j).getSlot().getDay().equals(day1))
                    {
                        for(int k = 0;k < reqSlotsProp.size();k++)
                        {
                            if(reqSlotsProp.get(k).getSlot().getDay().equals(day2))
                            {
                              if(reqSlotsProp.get(j).equalsWithoutDay(reqSlotsProp.get(k)))
                              {
                                  //in this point we found two equals req slot but in different days

                              }
                            }
                        }
                    }
                }
                */

                //Assumes the role really fits to slot(reqSlot) and to employee, and it's not a bug
                //Collections.shuffle(shiftsDay1);
                //Collections.shuffle(shiftsDay2);//Because we always switch the first ones
                /*
                int intDay1 = random.nextInt(days.size());
                int intDay2 = getRandomDay2(random, intDay1);
                Day day1 = days.get(intDay1);
                Day day2 = days.get(intDay2);
                ArrayList<Shift> shiftsDay1 = list.get(i).getShiftsByDay(day1);
                ArrayList<Shift> shiftsDay2 = list.get(i).getShiftsByDay(day2);

                int j = 0;
                for(int k = 0;k < shiftsDay1.size();k++)
                {
                    for(;j < shiftsDay2.size();j++)
                    {
                        if(shiftsDay1.get(k).getSlot().getStartTime().equals(
                                shiftsDay2.get(j).getSlot().getStartTime()
                        )&&
                                shiftsDay1.get(k).getSlot().getEndTime().equals(
                                        shiftsDay2.get(j).getSlot().getEndTime()
                                )&&
                                shiftsDay1.get(k).getRole().equals(
                                        shiftsDay2.get(j).getRole()
                                ))
                        {
                            shiftsDay1.get(k).getSlot().setDay(day2);
                            shiftsDay2.get(j).getSlot().setDay(day1);
                            String tempEmp1 = shiftsDay1.get(k).getEmployee().getFullName();
                            shiftsDay1.get(k).getEmployee().setFullName(shiftsDay2.get(j).getEmployee().getFullName());
                            shiftsDay2.get(j).getEmployee().setFullName(tempEmp1);
                            j = i + 1; //In order not to replace the same shift twice
                            break;
                        }
                    }

                    if(j == shiftsDay2.size())
                        j = 0;
                }

                 */
                ///////////////////

                ArrayList<Integer> indexToMutate = indexToMutate(
                        list.get(i), random);

                for (int j = 0;j < howMuchToMutate;j ++)
                    mutateMe(list.get(i), indexToMutate.get(j), random);
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

    private void mutateMe(Arrangement arrangement,
                          int indexShift,
                          Random random)
    {
        ArrayList<Day> tempDays = new ArrayList<>(days);
        Day dayInShift =  arrangement.getShifts().get(indexShift).getSlot().getDay();

        tempDays.removeIf(day -> day == dayInShift);
        int indexDay = random.nextInt(tempDays.size());
        Day newDay = tempDays.get(indexDay);
        arrangement.getShifts().get(indexShift).getSlot().setDay(newDay);
    }

    /*private int getRandomDay2(Random random, int intDay1)
    {
        Set<Day> tempDays = new HashSet(days);
        tempDays.remove(intDay1);
        return random.nextInt(tempDays.size());
    }*/
}
