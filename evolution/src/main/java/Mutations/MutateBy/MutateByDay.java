package Mutations.MutateBy;

import Model.Shift;

import java.time.DayOfWeek;

public class MutateByDay implements MutateBy<DayOfWeek> {
    @Override
    public DayOfWeek get(Shift shift) {
        return shift.getSlot().getDay();
    }

    @Override
    public void set(Shift shift, DayOfWeek value) {
        shift.getSlot().setDay(value);
    }
}
