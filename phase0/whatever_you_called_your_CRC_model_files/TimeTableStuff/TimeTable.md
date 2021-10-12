# Timetable
* This is a Use Case

## Collaborators
TimeTableManager
TimeTableObjects

## Functions
* Holds the TimeTableObject (if they exist). 
* Holds HashMaps of each day of the week for storage
  * 7 Keys in each day (Monday - Sunday) 
  * The values are the section classes in arraylist
* Schedule the sections to different keys in the hashmap
  * Check for conflicts in each TimeBlock when scheduling. 
  * Does not resolve conflict, user decides what to do with conflicts
