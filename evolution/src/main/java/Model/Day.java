package Model;

import java.time.DayOfWeek;

public enum Day {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;


    public static DayOfWeek getDay(int day)
    {
        return DayOfWeek.of(day);

//        switch(day)
//        {
//            case 1:
//                return SUNDAY;
//            case 2:
//                return MONDAY;
//            case 3:
//                return TUESDAY;
//            case 4:
//                return WEDNESDAY;
//            case 5:
//                return THURSDAY;
//            case 6:
//                return FRIDAY;
//            case 7:
//                return SATURDAY;
//        }
//
//        throw new RuntimeException("day is not between 1-7");
    }
}
