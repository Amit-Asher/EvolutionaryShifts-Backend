package Mutations;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Slot.ReqSlot;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class MutationByDay implements EvolutionaryOperator<Arrangement> {

    private double m_Probability;

    // actually mutation by employee
    private List<Employee> employees;

    public MutationByDay(double probability)
    {
        m_Probability = probability;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Arrangement> apply(List<Arrangement> arrangements, Random random) {

        for(int i = 0;i < arrangements.size();i++)
        {




            if (random.nextDouble() <= m_Probability) {
                arrangements.get(i).getShifts().get(
                        random.nextInt(arrangements.get(i).getShifts().size())
                ).setEmployee(employees.get(random.nextInt(employees.size())));
            }



                /*int randomIndex1 = random.nextInt(list.get(i).m_DaysOfWork2Levels.size());
                int day1 = list.get(i).m_DaysOfWork2Levels.get(randomIndex1).getKey();
                int randomIndex2 = random.nextInt(list.get(i).m_DaysOfWork2Levels.size());
                int day2 = list.get(i).m_DaysOfWork2Levels.get(randomIndex2).getKey();
                ArrayList<Shift> temp = list.get(i).getArrangement(day1);
                list.get(i).setArrangement(day1, list.get(i).getArrangement().get(day2));
                list.get(i).getArrangement().set(day2, temp);*/

                //change the day inside all shifts in day1 and day2

        }
        return arrangements;
    }
}
