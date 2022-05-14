package Rule.RuleSlots;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleConfig;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RuleSlots implements IRule
{
    private final List<RuleSlotsPreference> preferences = new ArrayList<>();


    @Override
    public void addPreference(JSONObject preference) {
//        preferences.add(new RuleSlotsPreference(
//        ))
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
        //return how many shifts from arrangement are valid(shift in emp-pref)
        double goodShifts = 0.0;

        for (RuleSlotsPreference pref : this.preferences) {
            ArrayList<Slot> employeeValidSlots = pref.getSlots();
            for (Slot employeeValidSlot : employeeValidSlots) {
                for (Shift shift : arrangement.getShifts()) {
                    if (employeeValidSlot.equals(shift.getSlot()))
                        goodShifts++;
                }
            }
        }

        goodShifts = goodShifts / arrangement.getShifts().size();
        goodShifts = goodShifts * 100;

        return goodShifts;
    }
}
