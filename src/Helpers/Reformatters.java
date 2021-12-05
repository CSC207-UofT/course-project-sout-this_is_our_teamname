package Helpers;

import java.util.LinkedHashMap;

/**
 * TODO DELETE THIS SENTENCE
 */
public class Reformatters {
    /**
     * Returns a hashmap of the entries in the string array strings with
     * corresponding integer values from least to greatest.
     *
     * @param strings the array of strings
     * @return a hashmap of items from strings as values and ascending
     * integers as keys
     */
    public static LinkedHashMap<String, String> hashMapIt(String[] strings) {
        LinkedHashMap<String, String> theMap = new LinkedHashMap<>();
        for (int i = 0; i < strings.length; i++) {
            theMap.put(String.valueOf(i), strings[i]);
        }
        return theMap;
    }
}
