package Rule.RuleRangeWorkDays;

import Model.Employee.Employee;

public class RuleRangeWorkDaysPreference {
    private Employee employee;
    private int minDays; //including
    private int maxDays; //including
    //or Range class

    public Employee getEmployee() {
        return employee;
    }

    public int getMinDays() {
        return minDays;
    }
    public int getMaxDays() {
        return maxDays;
    }
}
