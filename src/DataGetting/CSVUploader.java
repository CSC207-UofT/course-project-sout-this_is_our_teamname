package DataGetting;

import TimeTableObjects.EventObjects.Activity;
import TimeTableObjects.EventObjects.Task;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * A class that uploads a properly formatted reloadable
 * csv file to a timetable manager object
 */
public class CSVUploader extends DataGetter<Object> {

    /**
     * Constructor of the CSVUploader. Reads and filters the data correctly
     * into the data hashmap.
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
        ArrayList<String[]> data;
        try {
            data = ReadThroughFile(filepath);
        } catch (IOException e){
            throw new FileNotFoundException();
        }
        data.remove(0);
        for (String[] line : data) {
            if (!line[1].equals("Tasks")) {
                String[] times = line[1].split("-");
                if (line[2].equals("Activity")) {
                    Activity event = new Activity(LocalTime.of(Integer.parseInt(times[0].substring(0, 2)), 0, 0),
                            LocalTime.of(Integer.parseInt(times[1].substring(0, 2)), 0, 0), line[0], term, line[5]);
                    event.setName(line[3]);
                    placeToData(term, event);
                }
            }
            else {
                String[] FirstTwoRemoved = RemoveFirstTwo(line);
                for (String taskString : FirstTwoRemoved) {
                    Task task = new Task(LocalTime.of(0, 0, 0), LocalTime.of(23, 59, 59), line[0], term);
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
    public LinkedHashMap<String, Object> retrieveData(String courseName,
                                                      String term, String year) throws FileNotFoundException {
        CalibrateData(courseName, term, year);
        return super.getData();
    }

    /**
     * Process an array of strings to one which contains only meaningful data
     *
     * @param data the array needs to be processed
     */
    private String[] RemoveFirstTwo(String[] data) {
        String[] FirstTwoRemoved = new String[data.length - 2];
        for (int i = 0; i + 2 < data.length; i++) {
            FirstTwoRemoved[i] = data[i + 2];
        }
        return FirstTwoRemoved;
    }

    /**
     * Read and return the data contained in a csv file at specific location as
     * an array of arrays of strings
     *
     * @param filepath the path of the csv file that needs to be read
     */
    private ArrayList<String[]> ReadThroughFile(String filepath) throws IOException {
        ArrayList<String[]> AllDataLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String fileline;
        while ((fileline = reader.readLine()) != null) {
            String[] OneDataLine = fileline.split(",");
            AllDataLines.add(OneDataLine);
        }
        reader.close();
        return AllDataLines;
    }
}
