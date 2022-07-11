package Rule.RuleReqSlots;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.ReqSlot;
import Rule.IRule;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RuleReqSlots implements IRule
{
    private final List<RuleReqSlotsPreference> preferences = new ArrayList<>();

    public List<RuleReqSlotsPreference> getPreferences() {
        return this.preferences;
    }

    @Override
    public void addPreference(Employee employee, JSONObject preferenceJson) {
        this.preferences.add(new RuleReqSlotsPreference(employee, preferenceJson));

    }

    @Override
    public String getName() {
        return "RuleReqSlots";
    }

    @Override
    public double evaluate(Arrangement arrangement) {
        //Assumed that *all* employees participate in this Rule *once*
        //return how many shifts from arrangement are valid(shift in emp-pref)
        double goodShifts = 0.0;
        List<RuleReqSlotsPreference> preferences = this.preferences;
        ArrayList<ReqSlot> employeeValidReqSlots;

        for (RuleReqSlotsPreference pref : preferences) {
            employeeValidReqSlots = (ArrayList<ReqSlot>) pref.getReqSlots();
            for (ReqSlot employeeValidReqSlot : employeeValidReqSlots) {
                for (Shift shift : arrangement.getShifts()) {
                    //if (employeeValidSlot.equals(shift.getSlot()))
                    //  goodShifts++;

                    if(employeeValidReqSlot.getSlot().equals(shift.getSlot())
                            && employeeValidReqSlot.getRole().equals(shift.getRole()))
                        goodShifts++;
                }
            }
        }

        goodShifts = goodShifts / arrangement.getShifts().size();
        goodShifts = goodShifts * 100;

        return goodShifts;
    }
}