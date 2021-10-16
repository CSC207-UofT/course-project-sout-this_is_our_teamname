# DescriptionlessLife
(We may change the name of this object for obvious reasons)

## Class
* An **Entity**
### Extended from
* `TimeTableObject`

## Collaborators
* `TimeTable` Schedules the object
* `TimeTableManager` Creates the object from TimeTableObject

## Responsibilities
* Basically, a life object that does not have a description (like a reminder 
  or note)
* Should Hold:
  * Start Time [1]
  * End Time [1]
  * Location [1]
  * Date [1]
  * Term [1]

[1] = Stored in TimeTableObject class
