package TimeTableObjects.Parents;

import DataCollection.DataGetter;

import java.util.HashMap;
import java.util.Scanner;

public class SearchingData implements Entity{

    /**
     * Prints a hashmap in a way that allows the user to choose what to
     * section they want.
     *
     * @param dataObjects the hashmap of courses
     */
    private static void promptUserToChoose(HashMap<String, SearchingData> dataObjects){
        // TODO change to courses.values if needed!
        for (String dataKey : dataObjects.keySet()){
            System.out.println(dataKey);
        }
    }

    public SearchingData promptUser(DataGetter dataSource){
        // User enters the course they want to search
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String course = scanner.nextLine();

        // Gets the data from the datasource
        HashMap<String, SearchingData> course_data = dataSource.getData(course);
        promptUserToChoose(course_data);

        // The user enters the section they want to search
        Scanner userChoice = new Scanner(System.in);
        System.out.println("Please choose one: ");
        String selected = userChoice.nextLine();

        return course_data.get(selected);
    }
}
