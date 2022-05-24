package Mutations;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class MutationByEmployee implements EvolutionaryOperator<Arrangement> {

    private double m_Probability;

    private List<Employee> employees;

    public MutationByEmployee(double probability)
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
            if(random.nextDouble() <= m_Probability) {
                arrangements.get(i).getShifts().get(
                        random.nextInt(arrangements.get(i).getShifts().size())
                ).setEmployee(employees.get(random.nextInt(employees.size())));
            }
        }
        return arrangements;
    }
}
