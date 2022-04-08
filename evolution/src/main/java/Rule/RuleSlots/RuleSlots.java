package Rule.RuleSlots;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleConfig;

public class RuleSlots  implements IRule<RuleSlotsPreference>
{
    //protected ArrayList<RuleSlotsPreference> preferences = new ArrayList<>();
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
    }
}
