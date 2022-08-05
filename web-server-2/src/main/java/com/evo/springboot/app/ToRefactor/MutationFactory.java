package com.evo.springboot.app.ToRefactor;

import Arrangement.Arrangement;
import BusinessLogic.BusinessLogic;
import Model.Employee.Employee;
import Mutations.BasicMutation;
import Mutations.MutateBy.MutateByEmployee;
import Mutations.MutationDupsByEmployee;
import Mutations.MutationGenerateEmployee;
import Mutations.MutationSwapEmployees;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.stream.Collectors;

// todo: this class should be in evolution module but
// because our stateful design(!) we cant move it yet.
// otherwise, we are going to get circular dependency
public class MutationFactory {

    private class MutationTypes {
        public final static String MutationBySlot = "MutationBySlot";
        public final static String MutationByEmployee = "MutationByEmployee";
        public final static String MutationDupsByEmployee = "MutationDupsByEmployee";
        public final static String MutationSwapEmployees = "MutationSwapEmployees";
        public final static String MutationGenerateEmployee = "MutationGenerateEmployee";

    }
    public static EvolutionaryOperator<Arrangement> createMutation(String _mutationtype, JSONObject params) throws JSONException {
        // todo: improve design by remove switch case pattern
        // 1. define dictionary- string to class type
        // 2. get class by string and use reflection to get its Class
        // 3. use reflection to get the Constructor out of the Class
        // 4. create new instance with the constructor + json params

        EvolutionaryOperator<Arrangement> mutationToReturn;
        switch (_mutationtype) {
            case MutationFactory.MutationTypes.MutationBySlot:
                // todo: implement
                mutationToReturn = null;
                break;
            case MutationFactory.MutationTypes.MutationByEmployee:
                // todo: business logic should not be here
                mutationToReturn = new BasicMutation<Employee>(
                        params.getDouble("probability"),
                        BusinessLogic.getInstance().getActiveEmployees(BusinessLogic.staticCompName),
                        new MutateByEmployee()
                );
                break;
            case MutationFactory.MutationTypes.MutationDupsByEmployee:
                mutationToReturn = new MutationDupsByEmployee(
                        BusinessLogic.getInstance().getActiveEmployees(BusinessLogic.staticCompName)
                );
                break;
            case MutationFactory.MutationTypes.MutationSwapEmployees:
                mutationToReturn = new MutationSwapEmployees(
                        params.getDouble("probability"),
                        params.getInt("numberOfShiftsToChange"),
                        BusinessLogic.getInstance().getEmployeeSlotsPreference(BusinessLogic.staticCompName),
                        BusinessLogic.getInstance().getReqSlots(BusinessLogic.staticCompName)
                );
                break;
            case MutationFactory.MutationTypes.MutationGenerateEmployee:
                mutationToReturn = new MutationGenerateEmployee(
                        params.getDouble("probability"),
                        params.getInt("numberOfShiftsToChange"),
                        BusinessLogic.getInstance().getActiveEmployees(BusinessLogic.staticCompName)
                );
                break;
            default:
                throw new RuntimeException("unsupported mutation type: " + _mutationtype);
        }

        return mutationToReturn;
    }
}
