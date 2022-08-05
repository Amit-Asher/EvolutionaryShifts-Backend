package Rule;

import Rule.RuleFight.RuleFight;
import Rule.RuleFitEmpRole.RuleFitEmpRole;
import Rule.RulePeace.RulePeace;
import Rule.RuleRangeWorkDays.RuleRangeWorkDays;
import Rule.RuleReqSlots.RuleReqSlots;
import Rule.RuleSlots.RuleSlots;

public class RuleFactory {

    private class RuleTypes {
        public final static String RuleFight = "RuleFight";
        public final static String RuleFitEmpRole = "RuleFitEmpRole";
        public final static String RulePeace = "RulePeace";
        public final static String RuleRangeWorkDays = "RuleRangeWorkDays";
        public final static String RuleReqSlots = "RuleReqSlots";
        public final static String RuleSlots = "RuleSlots";
    }

    public static IRule createRule(String _ruletype) {

        IRule ruleToReturn;
        switch (_ruletype) {
            case RuleTypes.RuleFight:
                ruleToReturn = new RuleFight();
                break;
            case RuleTypes.RuleFitEmpRole:
                ruleToReturn = new RuleFitEmpRole();
                break;
            case RuleTypes.RulePeace:
                ruleToReturn = new RulePeace();
                break;
            case RuleTypes.RuleRangeWorkDays:
                ruleToReturn = new RuleRangeWorkDays();
                break;
            case RuleTypes.RuleReqSlots:
                ruleToReturn = new RuleReqSlots();
                break;
            case RuleTypes.RuleSlots:
                ruleToReturn = new RuleSlots();
                break;
            default:
                throw new RuntimeException("unsupported rule type: " + _ruletype);
        }

        return ruleToReturn;
    }
}
