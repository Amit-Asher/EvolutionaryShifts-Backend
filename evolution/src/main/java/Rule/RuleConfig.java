package Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleConfig<T> {
    /* THIS STRUCTURE AGGREGATES EMPLOYEES PREFERENCE (T) BEFORE EVALUATION */
    private final List<T> preferences = new ArrayList<>();

    public void addPreferences(T preference) {
        this.preferences.add(preference);
    }

    public List<T> getPreferences() {
        return preferences;
    }
}