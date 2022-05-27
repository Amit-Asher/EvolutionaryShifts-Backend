package Rule.RuleSlots;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.PrfSlot;
import Model.Slot.Slot;
import Rule.IRule;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

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

        double goodShifts = 0.0;

        // Set<Slot> didn't work as expected while using contains. so compare toStrings...
        Map<Employee, Set<String>> employeeSlotsMap = new HashMap<>();

        for (RuleSlotsPreference employeePreference : this.preferences) {
            for (PrfSlot employeeValidSlot : employeePreference.getSlots()) {
                if (!employeeSlotsMap.containsKey(employeePreference.getEmployee())) {
                    employeeSlotsMap.put(employeePreference.getEmployee(), new HashSet<>());
                }
                employeeSlotsMap.get(employeePreference.getEmployee()).add(employeeValidSlot.getSlot().toString());
            }
        }

        for (Shift shift : arrangement.getShifts()) {
            Set<String> employeeValidSlots = employeeSlotsMap.get(shift.getEmployee());
            if (employeeValidSlots.contains(shift.getSlot().toString())) {
                goodShifts++;
            }
        }

        goodShifts = goodShifts / arrangement.getShifts().size();
        goodShifts = goodShifts * 100;

        return goodShifts;
    }
}
