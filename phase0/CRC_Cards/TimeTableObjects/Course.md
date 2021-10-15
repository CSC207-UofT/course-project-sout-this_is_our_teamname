# Course

## Class
* `splittable` object
* This is a "twin" class of TimeTableObject, which stores course information.
* **DATA STRUCTURE** 

## Collaborators
* `DatabaseController` Creates the object

## Responsibilities
* Should Hold:
  * Section type (LEC/TUT/PRA)
  * Professor
  * Term (F/S/Y)
  * Time & Location (as a Collection; since there may be multiple time sections 
    of a course at different locations)
  * Delivery Method
  * Faculty Offering~~_
