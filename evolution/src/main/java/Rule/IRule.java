package Rule;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import org.json.JSONObject;

public interface IRule
{
    /* EVALUATE RULE FITNESS ON A GIVEN ARRANGEMENT */
    double evaluate(Arrangement arrangement);
    void addPreference(Employee employee, JSONObject preference);
    String getName();
}