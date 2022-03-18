package EvolutionaryShifts;

import java.util.ArrayList;
import java.util.Map;

public class RuleVSEmployees extends Rule{

    ArrayList<Map.Entry<Employee, Employee>> vs;
    // ArrayList<Map.Entry<Employee, Employee>> kind of prop

    public RuleVSEmployees(Rule rule) {
        super(rule);
    }

    @Override
    public void setM_Config(Preference preference) {

    }

    @Override
    public double Execute(Arrangement arrangeShifts) {
        return 0;
    }
}
