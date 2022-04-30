package Model.Slot;

import Model.Day;

import java.time.LocalDateTime;

public class Slot
{
    private Day day;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

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
