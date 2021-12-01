package DataLoading;

import TimeTableContainers.TimeTableManager;

import java.io.*;

/**
 * A DataLoader Superclass that connects to external sources to download data
 *
 * Precondition: The file needs to be uploaded is a properly formatted
 * csv file.
 *
 * TODO Delete this class! We only have one loader, so this is not needed!
 */
public abstract class DataLoader {

    /**
     * An abstract class to download the timetable manager object
     *
     * @param ttbmanager the timetable manager that needs to be downloaded
     * @param filename the name that users want to save the file as
     * @param year the year of the timetables that users input
     */
    abstract void download(TimeTableManager ttbmanager,
                           String filename, String year) throws IOException;
}
