package Rule.RuleFitEmpRole;


import Arrangement.Arrangement;
import Rule.IRule;
import Rule.RuleConfig;

public class RuleFitEmpRole  implements IRule<RuleFitEmpRolePreference> {
    private final RuleConfig<RuleFitEmpRolePreference> config = new RuleConfig<>();

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
    public void addPreference(RuleFitEmpRolePreference preference) {
        config.addPreferences(preference);
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
            if (arrangement.getShifts().get(i).getEmployee().
                    getFitRoles().contains(
                    arrangement.getShifts().get(i).getRole()
            ))
                finalGrade += gradeForFitRole;
        }

        return finalGrade;
    }
}
