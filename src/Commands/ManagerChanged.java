package Commands;

import TimeTableContainers.TimeTableManager;

/**
 * An interface for commands that changed the state of TimeTableManager
 */
public interface ManagerChanged {

    boolean managerChanged();

    TimeTableManager getManager();
}
