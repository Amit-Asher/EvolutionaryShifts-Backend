package Mutations;

import Arrangement.Arrangement;
import Model.Day;
import Model.Shift;
import Model.Slot.ReqSlot;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.*;

public class MutationByDay implements EvolutionaryOperator<Arrangement> {

    private double m_Probability;
    private ArrayList<ReqSlot> reqSlotsProp;
    private ArrayList<Day> days;
    //we can't use MutationByDay if days.size() == 1
    //days why not Set in ArrangementProperties??

    public MutationByDay(double probability)
    {
        m_Probability = probability;
    }

    @Override
    public List<Arrangement> apply(List<Arrangement> list, Random random) {

        for(int i = 0;i < list.size();i++)
        {
            if(random.nextDouble() <= m_Probability)
            {
                int intDay1 = random.nextInt(days.size());
                int intDay2 = getRandomDay2(random, intDay1);
                Day day1 = days.get(intDay1);
                Day day2 = days.get(intDay2);
                ArrayList<Shift> shiftsDay1 = list.get(i).getShiftsByDay(day1);
                ArrayList<Shift> shiftsDay2 = list.get(i).getShiftsByDay(day2);

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
            }
        }
        return list;
    }

    private int getRandomDay2(Random random, int intDay1)
    {
        Set<Day> tempDays = new HashSet(days);
        tempDays.remove(intDay1);
        return random.nextInt(tempDays.size());
    }
}
