package Model.Employee;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeePreferences
{
    //            ruleName
    private JSONObject preferences;
    private Employee employee;

    public EmployeePreferences(Employee employee, JSONObject preferences) {
        this.employee = employee;
        this.preferences = preferences;
    }

    public Map<String, JSONObject> getPreferences() {
        // todo: fix mismatch between json objects
        Map<String, JSONObject> preferences = new HashMap<>();
    }
}
