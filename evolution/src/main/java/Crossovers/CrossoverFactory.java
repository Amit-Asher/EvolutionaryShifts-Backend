package Crossovers;

import Arrangement.Arrangement;
import org.json.JSONException;
import org.json.JSONObject;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

public class CrossoverFactory {
    private class CrossoverTypes {
        public final static String BasicCrossover = "BasicCrossover";
    }

    public static AbstractCrossover<Arrangement> createCrossover(String _crossovertype, JSONObject params) throws JSONException {
        AbstractCrossover<Arrangement> crossoverToReturn;
        switch (_crossovertype) {
            case CrossoverTypes.BasicCrossover:
                crossoverToReturn = new BasicCrossover(params);
                break;
            default:
                throw new RuntimeException("unsupported crossover type: " + _crossovertype);
        }

        return crossoverToReturn;
    }
}