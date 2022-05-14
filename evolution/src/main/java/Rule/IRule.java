package Rule;

import Arrangement.Arrangement;
import org.json.JSONObject;

public interface IRule
{
    /* EVALUATE RULE FITNESS ON A GIVEN ARRANGEMENT */
    double evaluate(Arrangement arrangement);
    void addPreference(JSONObject preference);

    String getName();
}