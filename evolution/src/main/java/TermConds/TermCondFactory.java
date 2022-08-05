package TermConds;

import org.json.JSONException;
import org.json.JSONObject;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.termination.*;

public class TermCondFactory {
    private class TermCondType {
        public final static String GenerationCount = "GenerationCount";
        public final static String ElapsedTime = "ElapsedTime";
        public final static String Stagnation = "Stagnation";
        public final static String TargetFitness = "TargetFitness";
    }

    public static TerminationCondition createTerminationCondition(String _conditionType, JSONObject params) throws JSONException {
        // todo: improve design by remove switch case pattern
        // 1. define dictionary- string to class type
        // 2. get class by string and use reflection to get its Class
        // 3. use reflection to get the Constructor out of the Class
        // 4. create new instance with the constructor + json params

        TerminationCondition termCondToReturn;
        switch (_conditionType) {
            case TermCondType.GenerationCount:
                termCondToReturn = new GenerationCount(params.getInt("count"));
                break;
            case TermCondType.ElapsedTime:
                termCondToReturn = new ElapsedTime(params.getInt("maxDuration"));
                break;
            case TermCondType.Stagnation:
                termCondToReturn = new Stagnation(
                        params.getInt("generationLimit"),
                        true
                );
                break;
            case TermCondType.TargetFitness:
                termCondToReturn = new TargetFitness(
                        params.getDouble("targetFitness"),
                        true
                );
                break;
            default:
                throw new RuntimeException("unsupported mutation type: " + _conditionType);
        }

        return termCondToReturn;
    }
}
