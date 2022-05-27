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

    public DayOfWeek getDay() {
        return startTime.getDayOfWeek();
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

    @Override
    public boolean equals(Object object) {
//        try {
//            Slot slot = (Slot) object;
//            return this.startTime.equals(slot.startTime) &&
//                    this.endTime.equals(slot.endTime);
//        } catch (ClassCastException e) {
////            System.out.println("tov");
//        }
//        return false;
        Slot slot = (Slot) object;
        return this.startTime.equals(slot.startTime) &&
                this.endTime.equals(slot.endTime);
    }

    @Override
    public String toString() {
        return "Slot{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}