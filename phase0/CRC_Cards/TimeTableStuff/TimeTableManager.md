# TimeTableManager

## Class
* An **Use Case**

## Collaborators
* `Controllers` (receives the `TimeTableObjects` to schedule)
* `Course` (One data structure that passed from interface)
* `Section` (Created by `.split()` in `Course`, the object being scheduled)
* `TimeTableObject` + their children (the object being scheduled)
* `TimeTable` (Schedules the object into)

## Functions
* Stores and manages different `TimeTable` 
* Receives `Course` data structure and schedules corresponding sections to the timetable.
* Identifies the type of `TimeTableObjects` and schedules them to the correct `TimeTable`
* Reports and Responds if there are conflicts with existing events in `Timetable`
