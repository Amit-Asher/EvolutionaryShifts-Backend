package Crossovers;

import Arrangement.Arrangement;
import org.json.JSONException;
import org.json.JSONObject;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

public class CrossoverFactory {
    private class CrossoverTypes {
        public final static String BasicCrossover = "BasicCrossover";
        public final static String BasicRandomCrossover = "BasicRandomCrossover";
    }

    public static AbstractCrossover<Arrangement> createCrossover(String _crossovertype, JSONObject params) throws JSONException {
        AbstractCrossover<Arrangement> crossoverToReturn;
        switch (_crossovertype) {
            case CrossoverTypes.BasicCrossover:
                crossoverToReturn = new BasicCrossover(params);
                break;
            case CrossoverTypes.BasicRandomCrossover:
                crossoverToReturn = new BasicRandomCrossover(params.getInt("crossoverPoints"));
                break;
            default:
                throw new RuntimeException("unsupported crossover type: " + _crossovertype);
        }

        return crossoverToReturn;
    }
}