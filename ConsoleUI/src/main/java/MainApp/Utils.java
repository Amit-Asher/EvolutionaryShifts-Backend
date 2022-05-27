package MainApp;

import Model.Shift;
import Rule.RuleSlots.RuleSlotsPreference;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Utils {

    public static boolean isEmployeeSatisfied(Shift shift, List<RuleSlotsPreference> ruleSlotsPreference) {
        RuleSlotsPreference employeePreference = ruleSlotsPreference.stream()
                .filter(employeePref -> employeePref.getEmployee().equals(shift.getEmployee()))
                .findFirst().orElse(null);

        if(employeePreference == null) {
            System.out.println("[warn] [isEmployeeSatisfied] employeePreference is null");
            return false;
        }

        AtomicBoolean employeeSatisfied = new AtomicBoolean(false);
        employeePreference.getSlots().forEach(prfSlot -> {
           if (prfSlot.getSlot().equals(shift.getSlot())) {
               employeeSatisfied.set(true);
           }
        });

        return employeeSatisfied.get();
    }
}
