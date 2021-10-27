package EntitiesAndObjects.TimeTableObjects.Interfaces;

/**
 * Compareable is an interface implemented by Events and all its subclasses.
 * It compares 2 TimeTableObjects' time interval, returns -1 if they overlap, 1 otherwise.
 * @param <T> Another Events.
 */
public interface Comparable<T> {
    int compareTo(T o);
}
