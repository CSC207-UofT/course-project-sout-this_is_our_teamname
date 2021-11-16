package Helpers;

/**
 * A helper class that stores array search algorithms, such as Binary Search.
 */
public class Search {
    /**
     * A Quick Binary Search helper method to find queries in an array.
     *
     * @param query The item that you are trying to see if it is in the array
     * @param array The array to search
     * @return true iff the item is in the array
     */
    public static boolean BinarySearch(String query, String[] array){
        for (String item : array){
            if (query.equals(item)){
                return true;
            }
        }
        return false;
    }
}
