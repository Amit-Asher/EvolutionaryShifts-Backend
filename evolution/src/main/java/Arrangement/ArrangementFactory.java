package Arrangement;

import Model.Employee.Employee;
import Model.Shift;
import Model.Slot.ReqSlot;
import Rule.IRule;
import Rule.RuleSlots.RuleReqSlots;
import Rule.RuleSlots.RuleReqSlotsPreference;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ArrangementFactory extends AbstractCandidateFactory<Arrangement> {

    private ArrangementProperties arrangementProperties;

    public ArrangementFactory(ArrangementProperties arrangementProperties)
    {
        this.arrangementProperties = arrangementProperties;
    }


    @Override
    public Arrangement generateRandomCandidate(Random random)
    {
        ArrayList<Employee> activeEmployees = arrangementProperties.getActiveEmployees();
        ArrayList<ReqSlot> prefReqSlotsManager = arrangementProperties.getSlots();
        ArrayList<RuleReqSlotsPreference> ruleSlotsPreference = null;
        Map<IRule, Double> m_rule2weight = arrangementProperties.getRule2weight();
        for(Map.Entry<IRule, Double> entry : m_rule2weight.entrySet())
        {
            if(entry.getKey().getName().equals("RuleReqSlots"))
                ruleSlotsPreference = (ArrayList<RuleReqSlotsPreference>) ((RuleReqSlots)entry.getKey()).getConfig().getPreferences();
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
            ArrayList<Employee> activeEmployees,
            ArrayList<ReqSlot> prefReqSlotsManager,
            ArrayList<RuleReqSlotsPreference> ruleSlotsPreference,
            Random random)
    {
        Arrangement newArrangement = new Arrangement();

        for(ReqSlot reqSlotManager : prefReqSlotsManager)
        {
            ArrayList<RuleReqSlotsPreference> TempRuleSlotsPreference = new ArrayList<>(ruleSlotsPreference);

            while(TempRuleSlotsPreference.size() != 0)
            {
                int randIndexPref = random.nextInt(TempRuleSlotsPreference.size());
                ArrayList<ReqSlot> slotsInPref = TempRuleSlotsPreference.get(randIndexPref).getReqSlots();

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


