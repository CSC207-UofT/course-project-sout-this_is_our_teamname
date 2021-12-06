package DataGetting;

import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.Task;
import TimeTableObjects.Events;

import java.io.*;
import java.time.LocalTime;
import java.util.LinkedHashMap;

/**
 * TODO REMOVE THIS SENTENCE
 *
 * A class that uploads a properly formatted reloadable
 * csv file to a timetable manager object
 */
public class CSVUploader extends DataGetter<Object> {

    /**
     * Constructor of the CSVUploader. Reads and filters the data correctly
     * into the data hashmap.
     *
     * TODO Can you rename your variables? They are really weirdly named and
     * TODO does not convey to the reader what you are trying to say...
     *
     * @param filename the name of the file
     * @param term the term of the timetable
     * @param year the year of the timetable
     * @exception FileNotFoundException Throws file not found exception if the information for the
     * given information of the file is not found.
     */
    @Override
    public void CalibrateData(String filename, String term,
                              String year) throws FileNotFoundException {
        String filepath = "src\\OutputFiles\\" + filename + "_" + year + "_"  + term + ".csv";
        String[][] data;
        try {
            data = read(filepath);
        } catch (IOException e){
            throw new FileNotFoundException();
        }

        String[][] meaningfulData = meaningfulDataHelper(data);
        for (String[] row : meaningfulData) {
            if (!row[1].equals("Tasks")) {
                String[] times = row[1].split("-");
                if (row[2].equals("Activity")) {
                    Activity event = new Activity(LocalTime.of(Integer.parseInt(times[0].substring(0, 2)), 0, 0),
                            LocalTime.of(Integer.parseInt(times[1].substring(0, 2)), 0, 0), row[0], term, row[5]);
                    event.setName(row[3]);
                    placeToData(term, event);
                }
            }
            else {
                String[] smallMeaningfulData = smallMeaningfulDataHelper(row);
                for (String taskString : smallMeaningfulData) {
                    Task task = new Task(LocalTime.of(0, 0, 0), LocalTime.of(23, 59, 59), row[0], term);
                    task.setName(taskString);
                    placeToData(term, task);
                }
            }
        }
    }

    /**
     * A method to help get the data for from the loader
     * @param courseName the name of the Course
     * @param term the term of the course
     * @param year the year of the course
     * @return an arraylist of things for the loader
     * @throws FileNotFoundException if the file is not found
     */
    @Override
    LinkedHashMap<String, Object> retrieveData(String courseName, String term,
                                               String year) throws FileNotFoundException {
        CalibrateData(courseName, term, year);
        return super.getData();
    }

    /**
     * Process an array of arrays of strings to one which
     * contains only meaningful data
     *
     * @param data the array needs to be processed
     */
    private String[][] meaningfulDataHelper(String[][] data) {
        String[][] meaningfuldata = new String[1][data.length - 1];
        for (int i = 0; i + 1 < data.length; i++) {
            meaningfuldata[i] = data[i + 1];
        }
        return meaningfuldata;
    }

    /**
     * Process an array of strings to one which contains only meaningful data
     *
     * @param data the array needs to be processed
     */
    private String[] smallMeaningfulDataHelper(String[] data) {
        String[] smallMeaningfulData = new String[data.length - 2];
        for (int i = 0; i + 2 < data.length; i++) {
            smallMeaningfulData[i] = data[i + 2];
        }
        return smallMeaningfulData;
    }

    /**
     * Read and return the data contained in a csv file at specific location as
     * an array of arrays of strings
     *
     * TODO rename the variables see naming conventions in
     * TODO JavaAndExceptions.pdf in Week 4.!
     *
     * @param filepath the path of the csv file that needs to be read
     */
    private String[][] read(String filepath) throws IOException {
        // TODO Why not use an ArrayList<String[]>?
        String[][] dataplus = {};
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String row;
        while ((row = reader.readLine()) != null) {
            String[] data = row.split(",");
            dataplus = (String[][]) add(dataplus, data);
        }
        reader.close();
        return dataplus;
    }

    /**
     * Return a new array with one more object added to the original array
     *
     * TODO See comment in method `read`. Can delete this method
     *
     * @param objects the original object array
     * @param object the object that needs to be added
     */
    private Object[] add(Object[] objects, Object object) {
        Object[] newList = new Object[objects.length + 1];
        System.arraycopy(objects, 0, newList, 0, objects.length);
        newList[objects.length] = object;
        return newList;
    }
}
