package DataLoading;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * A class that downloads a timetable manager object to a properly formatted csv file
 *
 */
public class CSVDownloader{

    /**
     * Download a timetable manager object to a properly formatted csv file
     * that is reloadable but is less easy to read
     *
     * @param data the timetable manager that needs to be downloaded
     * @param calendarName the name that users want to save the file as
     */
    public void download(HashMap<String, List<List<String>>> data,
                         String calendarName) throws IOException {
        for (String term : data.keySet()) {
            String fileName = "src\\OutputFiles\\" + calendarName + "_" + term +
                    ".csv";
            FileWriter csvWriter = new FileWriter(fileName);

            StringBuilder heading = new StringBuilder();
            String[] headingString = {"Date", "Time", "Event Type",
                    "Event Name","Section Code", "Description"};
            for (String word : headingString) {
                heading.append(word).append(",");
            }

            csvWriter.append(heading.append("\n"));

            for (List<String> datalist : data.get(term)) {
                csvWriter.append(String.join(",", datalist));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        }
    }
}
