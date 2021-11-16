# CSVScraper

## Class
* Child Class of `DataGetter`
* Interface Adaptor (Database)

## Collaborators
* `Controllers` (calls on class to find data)
* `OperatorInterface` (sets the correct `DataGetter` Class)

## Responsibilities
* Reads a CSV file for the given course for information.
  * Throws an Error when the given course is not found.
* Analyze and filters the data and store the data as `Course` Objects
* Store the `Course` objects in a HashMap with section codes as keys and the 
  courses as values.
