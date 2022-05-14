package Rule.RulePeace;

import Arrangement.Arrangement;
import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleConfig;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class RulePeace implements IRule<RulePeacePreference> {
    private final RuleConfig<RulePeacePreference> config = new RuleConfig<>();

    @Override
    public double evaluate(Arrangement arrangement) {
        double finalGrade = 0.0;
        List<RulePeacePreference> preferences = config.getPreferences();

        DeleteDuplicates();
        for(RulePeacePreference pref : preferences)
        {
            double gradeForEmployee = 100;
            Employee employee = pref.getEmployee();
            ArrayList<DayOfWeek> daysOfWork = arrangement.getDaysOfWorkForEmployee(employee);
            ArrayList<Shift> shifts = arrangement.getShifts();
            int sizeShifts = shifts.size();
            boolean flagBreakSlot1;

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

                                if(shift2.getEmployee().equals(pref.getFriend())) {
                                    if (slot2.getDay().equals(day)) {
                                        // the two opponents work in the same day
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

                finalGrade += gradeForEmployee / preferences.size();
            }
        }

        return finalGrade;
    }

    @Override
    public void addPreference(RulePeacePreference preference) {
        config.addPreferences(preference);
    }

    @Override
    public String getName() {
        return "RulePeace";
    }

    private void DeleteDuplicates()
    {
        List<RulePeacePreference> Preferences = config.getPreferences();
        int sizePref = Preferences.size();

        for(int i = 0;i < sizePref;i++)
        {
            for(int j = 0;j < sizePref;j++)
            {
                if(Preferences.get(i).getEmployee().getFullName().equals(
                        Preferences.get(j).getFriend().getFullName()) &&
                        Preferences.get(j).getEmployee().getFullName().equals(
                                Preferences.get(i).getFriend().getFullName()))
                {
                    Preferences.remove(j);
                    sizePref--;
                    break;
                }
            }
        }
    }
}
