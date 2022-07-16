package Mutations.MutateBy;

import Model.Role;
import Model.Shift;

public class MutateByRole  implements MutateBy<Role>{
    @Override
    public Role get(Shift shift) {
        return shift.getRole();
    }

    @Override
    public void set(Shift shift, Role value) {
        shift.getRole().name = value.getName();
    }
}
