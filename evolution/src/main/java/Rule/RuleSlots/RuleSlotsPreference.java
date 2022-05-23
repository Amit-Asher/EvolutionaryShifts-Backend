package Rule.RuleSlots;

import Model.Employee.Employee;
import Model.Slot.PrfSlot;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class RuleSlotsPreference
{
    private ArrayList<PrfSlot> slots = new ArrayList<>();
    private Employee employee;

    public RuleSlotsPreference(Employee employee, JSONObject jsonObject) throws RuntimeException {
        try {
            Gson gson = new Gson();
            JSONArray slotsJson = (JSONArray) jsonObject.get("slots");
            for (int i = 0; i < slotsJson.length(); i++) {
                JSONObject slot = slotsJson.getJSONObject(i);
                PrfSlot prfSlot = gson.fromJson(String.valueOf(slot), PrfSlot.class);
                slots.add(prfSlot);
            }

            this.employee = employee;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to serialize RuleSlotsPreference");
        }
    }

    public ArrayList<PrfSlot> getSlots() {
        return slots;
    }

    public Employee getEmployee() {
        return employee;
    }
}
