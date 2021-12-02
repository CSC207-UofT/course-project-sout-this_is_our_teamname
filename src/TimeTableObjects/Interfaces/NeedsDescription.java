package TimeTableObjects.Interfaces;

import java.util.Scanner;

public interface NeedsDescription {
    static String buildDescription(){
        // Asks the user for the description of the object
        Scanner descriptionScanner = new Scanner(System.in);
        System.out.println("Please provide a description of your activity: ");
        return descriptionScanner.nextLine().toString();
    }

}
