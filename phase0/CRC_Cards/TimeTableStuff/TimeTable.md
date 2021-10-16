# Timetable

## Class
* A **Data Structure**

## Collaborators
* `TimeTableObjects` + its children (what it should store)
* `TimeTableManager` (calls the schedules' method on this class)
* `UserInterface` (Prints this object so the user can see what they have made)

## Responsibilities
* Holds the `TimeTableObject` (if they exist). 
* Holds `LinkedHashMaps` (for control of order) of each day of the week for 
  storage
  * 7 Keys in each day (Monday - Sunday) 
  * The values are the section classes in `Arraylist`
* Schedule the sections to different keys in the `LinkedHashMap` using a 
  `schedule` method (this method would behave like `put` for HashMap)
  * Check for conflicts in each `TimeTableObjects` when scheduling. 
  * Does not resolve conflict, sends conflicts to `TimeTableManager` to pass 
    to user decides what to do with conflicts
