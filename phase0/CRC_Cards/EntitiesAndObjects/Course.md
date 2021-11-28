# Course

## Class
* `sliceable` object
* A Data Structure

## Collaborators
* `InterfaceAdaptors` Requests the information and sends the object to 
  `TimeTableManager`
* `DataGathering` Creates the object
* `TimeTableManager` Splits the course into sections to place into `TimeTable`

## Responsibilities
* This is a "twin" class of TimeTableObject, which stores course information.
* Should Hold:
  * Section type (LEC/TUT/PRA)
  * Professor
  * Term (F/S/Y)
  * Time & Location (as a Collection; since there may be multiple time sections 
    of a course at different locations)
  * Delivery Method
  * Faculty Offering~~_
