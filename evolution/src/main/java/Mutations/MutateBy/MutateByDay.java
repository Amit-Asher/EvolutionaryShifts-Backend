package Mutations.MutateBy;

import Model.Day;
import Model.Shift;

public class MutateByDay implements MutateBy<Day> {
    @Override
    public Day get(Shift shift) {
        return shift.getSlot().getDay();
    }

    @Override
    public void set(Shift shift, Day value) {
        shift.getSlot().setDay(value);
    }
}
