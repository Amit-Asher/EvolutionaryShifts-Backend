package Rule.RuleReqSlots;

import Model.Employee.Employee;
import Model.Slot.ReqSlot;
import org.json.JSONObject;

import java.util.List;

public class RuleReqSlotsPreference
{
    private List<ReqSlot> slots;
    private Employee employee;

    public RuleReqSlotsPreference(Employee employee, JSONObject preferenceJson) {
    }

    public List<ReqSlot> getReqSlots() {
        return slots;
    }

    public Employee getEmployee() {
        return employee;
    }
}