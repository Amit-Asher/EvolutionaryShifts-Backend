package Rule.RuleFitEmpRole;


import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Rule.IRule;
import Rule.RuleSlots.RuleSlotsPreference;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RuleFitEmpRole  implements IRule {
    private final List<RuleFitEmpRolePreference> preferences = new ArrayList<>();

    /*
    * פתאום אני קולט שלחוק הזה לא צריך להחזיק שום מידע
    *כל המידע שאני צריך כבר נמצא בתוך הפתרון שמתקבל במתודה של איווליוט
    * בסך הכל עובר על כל המשמרות ומוריד נקודות על אי התאמה
    * בין התפקיד לעובד שלא מוכשר לתפקיד זה
    *
    * אני חושב שחוק זה אמור להיות Rule
    * ןאז IRule
    * יירש מ Rule
    * ואיפשהו למעלה יהיה לנו מערך של Rule
    * */

    @Override
    public void addPreference(Employee employee, JSONObject preferenceJson) {
        preferences.add(new RuleFitEmpRolePreference(employee, preferenceJson));
    }

    @Override
    public String getName() {
        return "RuleFitEmpRole";
    }

    @Override
    public double evaluate(Arrangement arrangement)
    {
        double finalGrade = 0, gradeForFitRole = 100.0 / arrangement.size();

        for(int i = 0;i < arrangement.size();i++) {
            Shift shift = arrangement.getShifts().get(i);
            if (shift.getEmployee().getFitRoles().contains(shift.getRole())) {
                finalGrade += gradeForFitRole;
            }
        }

        return finalGrade;
    }
}
