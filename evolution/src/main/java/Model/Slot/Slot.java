package Model.Slot;

import Model.Day;

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public DayOfWeek getDay() {
        return startTime.getDayOfWeek();
    }

    public boolean equals(Slot slot) {
        return this.startTime.equals(slot.startTime) &&
                this.endTime.equals(slot.endTime);
    }
}