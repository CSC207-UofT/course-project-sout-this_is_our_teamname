package TimeTableObjects;

/**
 * Compareable is an interface implemented by TimeTableObject and all its subclasses.
 * It compares 2 TimeTableObjects' time interval, returns true if they overlap, false otherwise.
 * @param <T> Another TimeTableObject.
 */
public interface Comparable<T> {
    boolean compareTo(T o);
}
