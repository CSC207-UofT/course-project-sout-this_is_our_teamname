package ZZZZDoNotMarkAnyCodeInThisFolder;

import TimeTableObjects.EventObjects.CourseSection;

import java.time.LocalTime;

/**
 * This class holds the once used compareTo method for the comparable interface, it is applicable to all Events classes
 * If you use this, please relocate the code outside this file to somewhere more appropriate.
 */
public class Comparable {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String date;
    private final String term;

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public String getTerm() {
        return term;
    }

    public Comparable(LocalTime startTime, LocalTime endTime, String date, String term) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.term = term;
    }

    /**
     * Compare two sections to check if they overlap in time.
     *
     * @param other The second section to be checked
     * @return -1 if they overlap, 1 otherwise.
     */
    public int compareTo(CourseSection other) {
        if (this.getDate().equals(other.getDate())) {
            int compare1 = this.getStartTime().compareTo(other.getStartTime());
            int compare2 = this.getEndTime().compareTo(other.getStartTime());
            int compare3 = this.getStartTime().compareTo(other.getEndTime());
            int compare4 = this.getEndTime().compareTo(other.getEndTime());
            if (( compare1 >= 0 && compare3 < 0) || (compare2 > 0 && compare4 <= 0)){
                return -1;
            }
            else {
                return 1;
            }
        }
        else {
            return 1;
        }
    }
}

