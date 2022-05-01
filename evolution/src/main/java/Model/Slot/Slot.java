package Model.Slot;

import Model.Day;

import java.time.LocalDateTime;

public class Slot
{
    private Day day;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Slot(Slot slot) {
        this.day = slot.getDay();
        this.startTime = slot.startTime;
        this.endTime = slot.endTime;
    }

    public Day getDay() {
        return day;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
