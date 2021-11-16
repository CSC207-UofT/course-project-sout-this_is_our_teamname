package Helpers;

/**
 * An exception that would be raised when there is a scheduling conflicts.
 */
public class ConflictException extends Exception{
    public ConflictException(){
        super("Scheduling conflict, it will not be scheduled");
    }
}
