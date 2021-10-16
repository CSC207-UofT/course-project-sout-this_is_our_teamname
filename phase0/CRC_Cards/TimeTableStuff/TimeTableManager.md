# TimeTableManager

## Class
* An **Use Case**

## Collaborators
* `DatabaseController` (receives the `TimeTableObjects` to schedule)
* `TimeTable` (Schedules the object into)
* `Course`/`TimeTableObject` + their children (the object it is scheduling)

## Functions
* Stores and manage `TimeTableObjects` by creating the correct object
* Schedules the courses by calling schedule method in `TimeTable`
* Report and Respond to conflicts
