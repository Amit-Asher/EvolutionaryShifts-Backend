package Selections;

import Arrangement.Arrangement;
import org.json.JSONException;
import org.json.JSONObject;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;

public class SelectionFactory {

    private class SelectionTypes {
        public final static String TournamentSelection = "TournamentSelection";
    }

    public static SelectionStrategy<? super Arrangement> createSelection(String _selectiontype, JSONObject params) throws JSONException {
        // todo: improve design by remove switch case pattern
        // 1. define dictionary- string to class type
        // 2. get class by string and use reflection to get its Class
        // 3. use reflection to get the Constructor out of the Class
        // 4. create new instance with the constructor + json params

        SelectionStrategy<? super Arrangement> selectionToReturn;
        switch (_selectiontype) {
            case SelectionFactory.SelectionTypes.TournamentSelection:
                selectionToReturn = new TournamentSelection(new Probability(params.getDouble("probability")));
                break;
            default:
                throw new RuntimeException("unsupported mutation type: " + _selectiontype);
        }

        return selectionToReturn;
    }
}
