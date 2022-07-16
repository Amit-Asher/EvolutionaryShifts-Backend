package Arrangement;

import Model.Employee.Employee;
import Model.Slot.ReqSlot;
import Rule.IRule;

import java.util.*;
import java.time.DayOfWeek;
import java.util.stream.IntStream;

public class ArrangementProperties
{
    private List<ReqSlot> reqSlots;
    private List<Employee> activeEmployees;
    private Map<IRule, Double> ruleName2weight;

    public ArrangementProperties(List<ReqSlot> slots,
                                 List<Employee> activeEmployees,
                                 Map<IRule, Double> rule2weight)
    {
        this.reqSlots = slots;
        this.activeEmployees = activeEmployees;
        this.ruleName2weight = rule2weight;
    }

    public List<ReqSlot> getReqSlots() {
        return reqSlots;
    }

    public void setReqSlots(List<ReqSlot> reqSlots) {
        this.reqSlots = reqSlots;
    }

    public List<Employee> getActiveEmployees() {
        return activeEmployees;
    }

    public void setActiveEmployees(List<Employee> activeEmployees) {
        this.activeEmployees = activeEmployees;
    }

    public Map<IRule, Double> getRuleName2weight() {
        return ruleName2weight;
    }

    public void setRuleName2weight(Map<IRule, Double> ruleName2weight) {
        this.ruleName2weight = ruleName2weight;
    }

    public Set<IRule> getRulesTypes() {
        return ruleName2weight.keySet();
    }

    /**
     * computed value from this.slots
     */
    public Set<DayOfWeek> getDays() {
        Set<DayOfWeek> days = new HashSet<>();
        IntStream.range(0, this.reqSlots.size()).forEach(idx -> {
            DayOfWeek day = this.reqSlots.get(idx).getSlot().getDay();
            days.add(day);
        });
        return days;
    }

    /**
     * computed value from this.slots
     */
    public int getNumOfDays() {
        return this.getDays().size();
    }
}
