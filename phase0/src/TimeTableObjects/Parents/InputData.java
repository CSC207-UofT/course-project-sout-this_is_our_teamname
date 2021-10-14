package TimeTableObjects.Parents;

import ConstantsAndExceptions.Constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class InputData implements Entity{
    private final LinkedHashMap<String, String> prompts;

    public InputData(){
        prompts = new LinkedHashMap<>();
        prompts.put(Constants.DATE, "Please enter the date of this object");
        prompts.put(Constants.START_TIME, "Please enter your the Start Time of " +
                "this object");
        prompts.put(Constants.END_TIME, "Please enter your the End Time of this " +
                "object");
        prompts.put(Constants.LOCATION, "Please enter the location of this " +
                "object");
    }

    public HashMap<String, String> getPrompt(){
        // User enters the time they want to search
        HashMap<String, String> retMap = new HashMap<>();

        for (String specification : prompts.keySet()) {
            Scanner promptScanner = new Scanner(System.in);
            System.out.println(specification + ": ");
            String receivedPrompt = promptScanner.nextLine();

            retMap.put(specification, receivedPrompt);
        }
        return retMap;
    }
}
