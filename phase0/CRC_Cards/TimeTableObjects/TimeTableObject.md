# TimeTableObject

## Class
* A Data Structure
* Behaves like an abstract class, but is not abstract, as it does not have 
  any abstract methods.
### Extended by:
* `Section`
* `DescriptionlessLife`
* `Life`

## Collaborators
* `TimeTableManager` ("Down Casts" the object into the correct "child class")
* `DatabaseController` (Creates the object, based on user inputs)

## Responsibilities
* A class to hold an object for a timetable to schedule.
* Stores:
    * Start Time
    * End Time
    * Location
    * Date
    * Term

