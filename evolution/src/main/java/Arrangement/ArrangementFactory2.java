package Arrangement;

import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.ReqSlot;
import Rule.IRule;
import Rule.RuleReqSlots.RuleReqSlots;
import Rule.RuleReqSlots.RuleReqSlotsPreference;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ArrangementFactory2 extends AbstractCandidateFactory<Arrangement> {

    private ArrangementProperties arrangementProperties;

    public ArrangementFactory2(ArrangementProperties arrangementProperties)
    {
        this.arrangementProperties = arrangementProperties;
    }


    @Override
    public Arrangement generateRandomCandidate(Random random)
    {
        ArrayList<Employee> activeEmployees = (ArrayList<Employee>) arrangementProperties.getActiveEmployees();
        ArrayList<ReqSlot> prefReqSlotsManager = (ArrayList<ReqSlot>) arrangementProperties.getSlots();
        ArrayList<RuleReqSlotsPreference> ruleSlotsPreference = null;
        Map<IRule, Double> m_rule2weight = arrangementProperties.getRule2weight();
        for(Map.Entry<IRule, Double> entry : m_rule2weight.entrySet())
        {
            if(entry.getKey().getName().equals("RuleReqSlots"))
                ruleSlotsPreference = (ArrayList<RuleReqSlotsPreference>) ((RuleReqSlots)entry.getKey()).getPreferences();
        }

        if(ruleSlotsPreference == null)
            throw new IllegalArgumentException("ArrangementFactory: there is not in the system RuleSlotsPreference");

        return buildNewArrangement(
                activeEmployees,
                prefReqSlotsManager,
                ruleSlotsPreference,
                random);

        //do we need to evaluate the new arrangement???
    }

    private Arrangement buildNewArrangement(
            List<Employee> activeEmployees,
            List<ReqSlot> prefReqSlotsManager,
            List<RuleReqSlotsPreference> ruleSlotsPreference,
            Random random)
    {
        Arrangement newArrangement = new Arrangement();

        for(ReqSlot reqSlotManager : prefReqSlotsManager)
        {
            ArrayList<RuleReqSlotsPreference> TempRuleSlotsPreference = new ArrayList<>(ruleSlotsPreference);

            while(TempRuleSlotsPreference.size() != 0)
            {
                int randIndexPref = random.nextInt(TempRuleSlotsPreference.size());
                ArrayList<ReqSlot> slotsInPref = (ArrayList<ReqSlot>) TempRuleSlotsPreference.get(randIndexPref).getReqSlots();

                if(slotsInPref.contains(reqSlotManager))
                {
                    newArrangement.addShift(new Shift(
                            TempRuleSlotsPreference.get(randIndexPref).getEmployee(),
                            reqSlotManager.getRole(),
                            reqSlotManager.getSlot()));
                    break;
                }
                else
                    TempRuleSlotsPreference.remove(randIndexPref);
            }

            if(TempRuleSlotsPreference.size() == 0)
            {
                //didn't find reqSlot fit for all the employees
                //we will make new one out of nowhere
                Employee randEmployee = null;

                while(activeEmployees.size() != 0)
                {
                    int randIndex = random.nextInt(activeEmployees.size());

                    if(activeEmployees.get(randIndex).getFitRoles().contains( reqSlotManager.getRole()))
                    {
                        randEmployee = activeEmployees.get(randIndex);
                        break;
                    }
                    else
                        activeEmployees.remove(randIndex);
                }

                if(randEmployee == null)
                    throw new IllegalArgumentException("ArrangementFactory: There is a ReqSlot for the manager that his Role doesn't much any of the employees");

                newArrangement.addShift(new Shift(
                        randEmployee,
                        reqSlotManager.getRole(),
                        reqSlotManager.getSlot()));
            }
        }

        return newArrangement;
    }
}