# Timetable

## Collaborators
* This is an Entity

## Functions
* Holds the TimeTableObject (if they exist).
* **TODO Determine the data structure! For instance, what will an empty timetable look like?**
* Holds Maps of each day of the week for storage
  * 5 Keys in each day (Monday - Friday) **TODO Determine what map to use. `Map` is an abstract class in Java. Should we use `Hashmap`?**
* Check for conflicts in each TimeBlock. 
* Is this still needed? Set an interval to control the size of the array 
* Deals with conflicts between scheduling
* Should be able to discern term of course if it is a course **TODO Should this be kept? This doesn't seem like the best architecture. Should we just make an abstract `schedule` method to `TimeTableObject` instead?**
