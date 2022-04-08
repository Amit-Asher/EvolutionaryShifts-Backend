package Rule.RuleSlots;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleConfig;

import java.util.ArrayList;
import java.util.List;

public class RuleSlots  implements IRule<RuleSlotsPreference>
{
    private final RuleConfig<RuleSlotsPreference> config = new RuleConfig<>();

    @Override
    public void addPreference(RuleSlotsPreference preference) {
        config.addPreferences(preference);
    }

    @Override
    public String getName() {
        return "RuleSlots";
    }

    @Override
    public double evaluate(Arrangement arrangement) {
        //old solution
        /**
         // todo: try to improve accuracy
         double goodShifts = 0;
         for (Shift shift : arrangement.getShifts())
         {
         Employee employee = shift.getEmployee();
         RuleSlotsPreference employeesPref = null;
         for (RuleSlotsPreference pref :  config.getPreferences()) {
         if (pref.employee.equals(employee)) {
         employeesPref = pref;
         }
         }

         if (employeesPref == null) {
         continue;
         }

         for (Slot slot : employeesPref.slots)
         {
         if(slot == shift.getSlot())
         {
         goodShifts ++;
         }
         }
         }

         return (goodShifts / arrangement.getShifts().size()) * 100;
         */

        //new solution
        //Assumed that *all* employees participate in this Rule *once*
        double finalGrade = 0.0, gradeForEmployee = 0.0;
        List<RuleSlotsPreference> Preferences = config.getPreferences();
        ArrayList<Shift> shifts = arrangement.getShifts();

        for (RuleSlotsPreference pref : Preferences) {
            for (Shift shift : shifts) {
                for (Slot slot : pref.getSlots()) {
                    if (slot.equals(shift.getSlot()))
                        gradeForEmployee++;
                }
            }

            gradeForEmployee = gradeForEmployee / shifts.size();
            gradeForEmployee = gradeForEmployee * 100;
            gradeForEmployee = gradeForEmployee / Preferences.size();
            finalGrade += gradeForEmployee;
        }

        return finalGrade;
    }
}
