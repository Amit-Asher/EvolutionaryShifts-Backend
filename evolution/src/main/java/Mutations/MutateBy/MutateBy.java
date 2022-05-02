package Mutations.MutateBy;

import Model.Shift;

public interface MutateBy<T> {
    T get(Shift shift);
    void set(Shift shift, T value);
}
