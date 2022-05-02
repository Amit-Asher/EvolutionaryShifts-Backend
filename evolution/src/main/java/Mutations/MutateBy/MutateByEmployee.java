package Mutations.MutateBy;

import Model.Day;
import Model.Employee.Employee;
import Model.Shift;

public class MutateByEmployee implements MutateBy<Employee>{
    @Override
    public Employee get(Shift shift) {
        return shift.getEmployee();
    }

    @Override
    public void set(Shift shift, Employee value) {
        shift.setEmployee(value);
    }
}
