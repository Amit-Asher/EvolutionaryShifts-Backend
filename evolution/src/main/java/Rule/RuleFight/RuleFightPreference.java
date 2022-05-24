package Rule.RuleFight;

import Model.Employee.Employee;
import org.json.JSONException;
import org.json.JSONObject;

public class RuleFightPreference
{
    private Employee employee;
    private String opp;


    public RuleFightPreference(Employee employee, JSONObject jsonObject) throws RuntimeException {
        this.employee = employee;

        try {
            opp = (String) jsonObject.get("opp");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to serialize RuleFightPreference");
        }

    }
    public Employee getEmployee() {
        return employee;
    }

    public String getOpp() {
        return opp;
    }
}
