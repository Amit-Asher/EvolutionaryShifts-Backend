package Rule.RuleRangeWorkDays;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Rule.IRule;
import Rule.RuleConfig;
import Rule.RuleSlots.RuleSlotsPreference;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RuleRangeWorkDays implements IRule {
    private final List<RuleRangeWorkDaysPreference> config = new ArrayList<>();

    @Override
    public double evaluate(Arrangement arrangement)
    {
        double finalGrade = 100.0;
        //Assume in preferences there isn't twice the same employee
        List<RuleRangeWorkDaysPreference> preferences = config;
        int sizePref = preferences.size();
        for(RuleRangeWorkDaysPreference pref : preferences)
        {
            int daysOfWorkForEmployee = arrangement.getDaysOfWorkForEmployee(pref.getEmployee()).size();

            if(daysOfWorkForEmployee < pref.getMinDays() ||
                    daysOfWorkForEmployee > pref.getMaxDays())
            {
                finalGrade -= 100.0 / sizePref;
            }
        }

        return finalGrade;
    }

    @Override
    public void addPreference(Employee employee, JSONObject preference) throws RuntimeException {
        config.add(new RuleRangeWorkDaysPreference(employee, preference));
    }

    @Override
    public String getName() {
        return "RuleRangeWorkDays";
    }
}
