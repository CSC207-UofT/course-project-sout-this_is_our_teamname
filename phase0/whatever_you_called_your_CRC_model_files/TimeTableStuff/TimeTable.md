# Timetable

## Collaborators
* This is an Entity

## Functions
* Holds the TimeTableObject (if they exist). 
* Holds HashMaps of each day of the week for storage
  * 5 Keys in each day (Monday - Friday) 
* Check for conflicts in each TimeBlock. 
* Is this still needed? Set an interval to control the size of the array 
* Deals with conflicts between scheduling
* Should be able to discern term of course if it is a course **TODO Should this be kept? This doesn't seem like the best architecture. Should we just make an abstract `schedule` method to `TimeTableObject` instead?**
