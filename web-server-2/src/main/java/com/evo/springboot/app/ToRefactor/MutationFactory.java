package com.evo.springboot.app.ToRefactor;

import Arrangement.Arrangement;
import BusinessLogic.BusinessLogic;
import Model.Employee.Employee;
import Mutations.BasicMutation;
import Mutations.MutateBy.MutateByEmployee;
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
                List<Employee> allEmployees = BusinessLogic.getInstance()
                        .getAllEmployees(BusinessLogic.staticCompName);
                List<Employee> activeEmployees = allEmployees.stream().filter(employee -> allEmployees.contains(employee.getID())).collect(Collectors.toList());
                mutationToReturn = new BasicMutation<Employee>(
                        params.getDouble("probability"),
                        activeEmployees,
                        new MutateByEmployee()
                );
                break;
            default:
                throw new RuntimeException("unsupported mutation type: " + _mutationtype);
        }

        return mutationToReturn;
    }
}
