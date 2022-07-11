package Rule.RulePeace;

import Model.Employee.Employee;
import org.json.JSONException;
import org.json.JSONObject;

public class RulePeacePreference {
    private Employee employee;
    private String friend;//probably should change when we hace db


    public RulePeacePreference(Employee employee, JSONObject jsonObject) throws RuntimeException {
        this.employee = employee;
        try {
            friend = (String) jsonObject.get("friend");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to serialize RulePeacePreference");
        }
    }
    public Employee getEmployee() {
        return employee;
    }

    public String getFriend() {
        return friend;
    }
}
