package Rule.RulePeace;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleConfig;
import Rule.RuleSlots.RuleSlotsPreference;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RulePeace implements IRule {
    private final List<RulePeacePreference> config = new ArrayList<>();

    @Override
    public double evaluate(Arrangement arrangement) {
        double finalGrade = 0.0;
        List<RulePeacePreference> preferences = config;
        Set<Employee> allEmp = arrangement.getWorkingEmp();
        DeleteDuplicates(allEmp);
        for(RulePeacePreference pref : preferences)
        {
            double gradeForEmployee = 100;
            Employee employee = pref.getEmployee();
            ArrayList<DayOfWeek> daysOfWork = arrangement.getDaysOfWorkForEmployee(employee);
            List<Shift> shifts = arrangement.getShifts();
            int sizeShifts = shifts.size();
            boolean flagBreakSlot1;
            Employee friend = getFriend(allEmp,pref.getFriend());
            for (DayOfWeek day : daysOfWork)
            {
                flagBreakSlot1 = false;

                for(int i = 0;i < sizeShifts;i++)
                {
                    Shift shift1 = shifts.get(i);
                    Slot slot1 = shift1.getSlot();

                    if(shift1.getEmployee().equals(employee)) {
                        if (slot1.getDay().equals(day)) {
                            for (int j = i + 1; j < sizeShifts; j++) {
                                Shift shift2 = shifts.get(j);
                                Slot slot2 = shift2.getSlot();

                                if(shift2.getEmployee().equals(friend)) {
                                    if (slot2.getDay().equals(day)) {
                                        // the two friends work in the same day
                                        //It remains to be seen if they overlap during working hours
                                        if (!((slot1.getStartTime().isBefore(slot2.getEndTime()) &&
                                                slot2.getEndTime().isBefore(slot1.getEndTime())) ||
                                                (slot1.getStartTime().isBefore(slot2.getStartTime()) &&
                                                        slot2.getStartTime().isBefore(slot1.getEndTime()))))
                                        {
                                            gradeForEmployee -= 100.0 / daysOfWork.size();
                                            flagBreakSlot1 = true;
                                            break;
                                        }
                                    }
                                }
                            }

                            if(flagBreakSlot1)
                                break;
                        }
                    }
                }
                if(flagBreakSlot1)
                    break;
            }
            finalGrade += gradeForEmployee / preferences.size();
        }

        return finalGrade;
    }


    private Employee getFriend(Set<Employee> allEmp,String firendName){
        Employee friend = null;
        for(Employee emp :allEmp){
            if(emp.getFullName().equals(firendName)) {
                friend = emp;
                break;
            }
        }
        return friend;
    }
    @Override
    public void addPreference(Employee employee, JSONObject preference) {
        config.add(new RulePeacePreference(employee, preference));
    }

    @Override
    public String getName() {
        return "RulePeace";
    }

    private void DeleteDuplicates(Set<Employee> allEmp)
    {
        List<RulePeacePreference> Preferences = config;
        int sizePref = Preferences.size();

        for(int i = 0;i < sizePref;i++)
        {
            for(int j = 0;j < sizePref;j++)
            {
                Employee friend = getFriend(allEmp,Preferences.get(j).getFriend());
                if(Preferences.get(i).getEmployee().getFullName().equals(
                        friend.getFullName()) &&
                        Preferences.get(j).getEmployee().getFullName().equals(
                                friend.getFullName()))
                {
                    Preferences.remove(j);
                    sizePref--;
                    break;
                }
            }
        }
    }
}
