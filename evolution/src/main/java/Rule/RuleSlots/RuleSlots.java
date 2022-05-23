package Rule.RuleSlots;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.PrfSlot;
import Model.Slot.Slot;
import Rule.IRule;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RuleSlots implements IRule
{
    private final List<RuleSlotsPreference> preferences = new ArrayList<>();

    /**
     * FOR TESTING
     */
    public List<RuleSlotsPreference> getPreferences() {
        return preferences;
    }

    @Override
    public void addPreference(Employee employee, JSONObject preferenceJson) throws RuntimeException {
        preferences.add(new RuleSlotsPreference(employee, preferenceJson));
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

        // new solution
        // Assumed that *all* employees participate in this Rule *once*
        // return how many shifts from arrangement are valid (shift in emp-pref)
        double goodShifts = 0.0;
        int i = 0;
        int j = 0;

        for (RuleSlotsPreference pref : this.preferences) { // outer loop iterates employees
            ArrayList<PrfSlot> employeeValidSlots = pref.getSlots();
            for (PrfSlot employeeValidSlot : employeeValidSlots) { // middle loop iterates employee slots
                for (Shift shift : arrangement.getShifts()) { // inner loop iterates all current shifts
                    if (employeeValidSlot.getSlot().equals(shift.getSlot()) &&
                            employeeValidSlot.getRole().getName().equals(shift.getRole().getName()) &&
                            pref.getEmployee().getFullName().equals(shift.getEmployee().getFullName())) {
                        goodShifts++;
                        break;
                    }
                }
                i++;
            }
        }

        System.out.println(i);
        System.out.println(arrangement.getShifts().size());
        goodShifts = goodShifts / arrangement.getShifts().size();
        goodShifts = goodShifts * 100;

        return goodShifts;
    }
}
