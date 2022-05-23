package Rule.RuleSlots;

import Model.Employee.Employee;
import Model.Slot.ReqSlot;

import java.util.ArrayList;

public class RuleReqSlotsPreference
{
    private ArrayList<ReqSlot> slots;
    private Employee employee;

    public ArrayList<ReqSlot> getReqSlots() {
        return slots;
    }

    public Employee getEmployee() {
        return employee;
    }
}
