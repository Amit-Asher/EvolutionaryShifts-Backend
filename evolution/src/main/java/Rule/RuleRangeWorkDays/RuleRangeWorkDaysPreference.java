package Rule.RuleRangeWorkDays;

import Model.Employee.Employee;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RuleRangeWorkDaysPreference {
    private Employee employee;
    private int minDays; //including
    private int maxDays; //including
    //or Range class


    public RuleRangeWorkDaysPreference(Employee employee, JSONObject jsonObject) throws RuntimeException {
        this.employee = employee;

        try {
            minDays = (Integer) jsonObject.get("minDays");
            maxDays = (Integer) jsonObject.get("maxDays");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to serialize RuleRangeWorkDaysPreference");
        }

    }
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
