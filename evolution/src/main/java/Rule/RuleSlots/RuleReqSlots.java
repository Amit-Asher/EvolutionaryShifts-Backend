package Rule.RuleSlots;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.ReqSlot;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleConfig;

import java.util.ArrayList;
import java.util.List;

public class RuleReqSlots implements IRule<RuleReqSlotsPreference>
{
    private final RuleConfig<RuleReqSlotsPreference> config = new RuleConfig<>();

    public RuleConfig<RuleReqSlotsPreference> getConfig() {
        return config;
    }

    @Override
    public void addPreference(RuleReqSlotsPreference preference) {
        config.addPreferences(preference);
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
        List<RuleReqSlotsPreference> preferences = config.getPreferences();
        ArrayList<ReqSlot> employeeValidReqSlots;

        for (RuleReqSlotsPreference pref : preferences) {
            employeeValidReqSlots = pref.getReqSlots();
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
