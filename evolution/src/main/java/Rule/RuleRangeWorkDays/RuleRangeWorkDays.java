//package Rule.RuleRangeWorkDays;
//
//import Arrangement.Arrangement;
//import Rule.IRule;
//import Rule.RuleConfig;
//
//import java.util.List;
//
//public class RuleRangeWorkDays implements IRule<RuleRangeWorkDaysPreference> {
//    private final RuleConfig<RuleRangeWorkDaysPreference> config = new RuleConfig<>();
//
//    @Override
//    public double evaluate(Arrangement arrangement)
//    {
//        double finalGrade = 100.0;
//        //Assume in preferences there isn't twice the same employee
//        List<RuleRangeWorkDaysPreference> preferences = config.getPreferences();
//        int sizePref = preferences.size();
//        for(RuleRangeWorkDaysPreference pref : preferences)
//        {
//            int daysOfWorkForEmployee = arrangement.getDaysOfWorkForEmployee(pref.getEmployee()).size();
//
//            if(daysOfWorkForEmployee < pref.getMinDays() ||
//                    daysOfWorkForEmployee > pref.getMaxDays())
//            {
//                finalGrade -= 100.0 / sizePref;
//            }
//        }
//
//        return finalGrade;
//    }
//
//    @Override
//    public void addPreference(RuleRangeWorkDaysPreference preference) {
//        config.addPreferences(preference);
//    }
//
//    @Override
//    public String getName() {
//        return "RuleRangeWorkDays";
//    }
//}
