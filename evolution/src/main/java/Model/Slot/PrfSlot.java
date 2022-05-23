package Model.Slot;

import Model.Role;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrfSlot {
    /**
     * THIS CLASS IS OFTEN DESERIALIZED FROM JSON.
     * fields are strings and not Slot/LocalDateTime
     * in order to simplify the integration with Gson
     */
    public String role;
    public String startTime;
    public String endTime;

    public Slot getSlot() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(this.startTime, formatter);
            LocalDateTime endTime = LocalDateTime.parse(this.endTime, formatter);
            return new Slot(startTime, endTime);
        } catch (Exception e) {
            System.out.println("ok");
        }
        return null;
    }

    public Role getRole() {
        return new Role(this.role);
    }
}