package Model.Slot;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Slot
{
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Slot(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Slot(Slot slot) {
        this.startTime = slot.startTime;
        this.endTime = slot.endTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public DayOfWeek getDay() {
        return startTime.getDayOfWeek();
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setDay(DayOfWeek day)
    {
        LocalDateTime temp = startTime;
        startTime = temp.plusHours(24L * (day.getValue() -
                startTime.getDayOfWeek().getValue()));
        temp = endTime;
        endTime = temp.plusHours(24L * (day.getValue() -
                endTime.getDayOfWeek().getValue()));
    }
}
