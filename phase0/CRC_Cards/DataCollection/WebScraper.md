# WebScraper

## Class
* Child Class of `DataGetter`
* Interface Adaptor (Database)

## Collaborators
* `DatabaseController` (calls on class to find data)
* `OperatorInterface` (sets the correct `DataGetter` Class)

## Functions
* Reads the HTML of the UofT Course finder for the giver course.
  * Throws an Error when the given course is not found.
* Analyze and filters the data and store the data as a Course Objects
* Store the course objects in a HashMap with section codes as keys and the 
  courses as values.
