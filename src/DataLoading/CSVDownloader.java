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
     * @param filename the name that users want to save the file as
     */
    public void download(HashMap<String, List<List<String>>> data, String filename) throws IOException {
        for (String term : data.keySet()) {
            FileWriter csvWriter = new FileWriter("src\\OutputFiles\\"
                    + filename + "_" + term + ".csv");

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
