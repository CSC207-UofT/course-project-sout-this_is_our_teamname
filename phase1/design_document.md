# Design Document

## Major Changes Log
### Object Name Changes
* `TimetableObject` -> `Event`
* TODO

## UML Diagram
[The Diagram](https://docs.google.com/presentation/d/1Y4G68pZL0exLt_WOKD81JUoqP-viaEZpGGOaLyKMd6M/edit#slide=id.p)

[Alternate](https://docs.google.com/presentation/d/1WdHyNQxlsQ0VAOPu0ujWFXbWTQDfYz4l92REhfZY83A/edit?usp=sharing)

## Walk Through 2.0
(Please see Phase 0 Walk Through for more details regarding phase 0 
components. This section will only elaborate on new features, while briefly 
mentioning features of old features)

### Scenario
For this Walk Though, consider a user named GeorgeJohnSmith. He is a super 
powerful student at UofT who is in 5 different programs at the same time and 
is planning to enroll in 5 courses.
* `CSC207H1` (Software Design - Faculty of Arts and Science)
* `APS111H1` (Engineering Strategies and Practice I - Faculty of Applied 
  Science and Engineering)
* `TMU131H1` (Music Theory I - Faculty of Music)
* `ARC100H1` (Drawing and Representation I - John H. Daniels Faculty of 
  Landscape Design and Architecture)
* `ANA126Y1` (Elementary Human Anatomy - Faculty of Kinesiology and Physical 
  Education)

But he has a problem (No it's not how he will survive; that is a separate 
problem). He wants to schedule these courses, but there are no tools that he 
can use at UofT that can schedule across all 5 faculties (NB: At least in 
the faculty of engineering, ACORN doesn't let you enroll in core courses; 
that's up to the Registrar/Department to do, so that's not an option). So he 
uses our fancy tool to do so.

### Steps
1. **TODO ADD UI AND OI DETAILS**
2. The user now will be prompted by `DatabaseController` to select an action 
   from a list of commands we have implemented.
   For our example, the GeorgeJohnSmith will choose the `GenerateTimeTable` 
   function. He will then be prompted to enter the course names of each of 
   his courses. So he enters the following:
   1. `CSC207H1`
   2. `APS111H1`
   3. `TMU131H1`
   4. `ARC100H1`
   5. `ANA126Y1`  
   
   `DatabaseController` will then search using `DataGetter` class to get the 
   correct information into a HashMap. Then, `DatabaseController` will create 
   the command object `SolverCommand` using (@Sonny TODO Perhaps Builder 
   here?). It will then invoke the command by using the `execute()` method, 
   as per the description for the command design pattern.

3. TODO

## Design Patterns
Here is a list of where we used Design Patterns in our project
* `Command`: This is a use of a Command Pattern (obviously)


