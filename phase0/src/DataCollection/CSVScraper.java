package DataCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CSVScraper extends DataGetter {

    public CSVScraper(String course_name){
        super(course_name);
    }

    /**
     * Read the file given by filename.
     *
     * Parts of this code were modified from
     * `https://www.w3schools.com/java/java_files_read.asp`
     *
     * Precondition: Filename is a valid filename
     *
     * @return an arraylist of all the lines of the code at filename
     * @param filename the name of the file
     */
    private ArrayList<String> readFile(String filename){
        ArrayList<String> readData = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                readData.add(myReader.nextLine());
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return readData;
    }



    @Override
    public void configureData() {
        this.data = new HashMap<>();
        ArrayList<String> filedata =
                readFile("DataCollection\\SampleDirectory" +
                        this.course_name);

        // TODO filter the data
    }
}
