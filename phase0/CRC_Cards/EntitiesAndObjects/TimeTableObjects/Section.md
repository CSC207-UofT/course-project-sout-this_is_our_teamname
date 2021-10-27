# Section

## Class
* A child class of `TimeTableObject`
* An **Entity**

## Collaborators
* Created from split method in `Course` by `TimeTableManager`
* Scheduled into TimeTable 
* Comparable with `Section` (itself)

## Responsibilities
* Stores a single course data (single time, single location)
* Can be placed into timetable
* Can check for conflicts
* Stores:
  * Start Time [1]
  * End Time [1]
  * Location [1]
  * Date [1]
  * Term [1]
  * Section code
  * Professor
  * Faculty
  * Delivery Method

[1] = Stored in TimeTableObject
