package GlobalHelperMethods;

import java.sql.Time;

public class StringToTime {
    /** Turns a string in the format "13:00pM" to 13:00:00
     *
     * @param timeString A string representing time
     * @return a Time object
     */
    public static Time makeTime(String timeString) {
        String[] timeStringSplit = timeString.split(":");
        String stringHour = timeStringSplit[0];
        String stringMinute = timeStringSplit[1].substring(0, 2);
        String indicator = timeStringSplit[1].substring(2, 4);

        int hour = Integer.parseInt(stringHour);
        int minute = Integer.parseInt(stringMinute);

        if (indicator.equals("AM")) {
            if (hour == 12) {
                return new Time(0, minute, 0);
            } else {
                return new Time(hour, minute, 0);
            }
        } else if (indicator.equals("PM")) {
            if (hour == 12) {
                return new Time(12, minute, 0);
            } else {
                return new Time(12 + hour, minute, 0);
            }
        } else {
            return null;
        }
    }
}
