package Rule;

import Rule.IRule;
import Rule.RuleFight.RuleFight;
import Rule.RuleFitEmpRole.RuleFitEmpRole;
import Rule.RuleRangeWorkDays.RuleRangeWorkDays;
import Rule.RuleSlots.RuleSlots;

public class RuleFactory {

    private class RuleTypes {
        public final static String RuleSlots = "RuleSlots";
        public final static String RuleRangeWorkDays = "RuleRangeWorkDays";
        public final static String RuleFight = "RuleFight";
        public final static String RuleFitEmpRole = "RuleFitEmpRole";
    }

    public static IRule createRule(String _ruletype) {

        IRule ruleToReturn;
        switch (_ruletype) {
            case RuleTypes.RuleSlots:
                ruleToReturn = new RuleSlots();
                break;
            case RuleTypes.RuleRangeWorkDays:
                ruleToReturn = new RuleRangeWorkDays();
                break;
            case RuleTypes.RuleFight:
                ruleToReturn = new RuleFight();
                break;
            case RuleTypes.RuleFitEmpRole:
                ruleToReturn = new RuleFitEmpRole();
                break;
            default:
                throw new RuntimeException("unsupported rule type: " + _ruletype);
        }

        return ruleToReturn;
    }
}
