package EvolutionaryShifts.Rule.RuleSlots;

import EvolutionaryShifts.*;
import EvolutionaryShifts.Arrangement.Arrangement;
import EvolutionaryShifts.Rule.Preference;
import EvolutionaryShifts.Rule.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RuleSlots extends Rule
{
    protected ArrayList<RuleSlotsPreference> preferences = new ArrayList<>();

    @Override
    public void addPreference(Preference pref) {
        // todo: ask Aviad for best practices of polymorphism (maybe generics instead of Preference marker?)
        preferences.add((RuleSlotsPreference) pref);
    }

    @Override
    public double Execute(Arrangement arrangement)
    {
        // todo: try to improve accuracy

        double goodShifts = 0;

        for (Shift shift : arrangement.getShifts())
        {
            Employee employee = shift.getEmployee();
            RuleSlotsPreference employeesPref = null;
            for (RuleSlotsPreference pref : preferences) {
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
