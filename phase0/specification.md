# Specifications

## Introduction
The timetable tool is one of the most widely used tools for students in the 
Faculty of Arts and Science. It provides a well documented database of all 
the course offerings for specific terms, as well as provide a tool for 
students to informally draft timetables. However, the tool has many design 
problems that makes it difficult to use. It's User Interface is not very 
user-friendly, as well as it is limited in the functionalities that it 
provides outside of search and planning. It is also restrictive to only the 
Faculty of Arts and Science, which restricts user pool to only Arts and 
Science Students. In this project, we hope to provide a new design for a 
timetable tool that can resolve some issues found in timetable tool and 
expand it to all faculties within the University of Toronto (within the 
scope of the CourseFinder).

## The Need
A basic functions that the tool should have is a storage system for 
timetables. It should support holding timetables for multiple semesters with 
a minimum of storing 2 timetables (one for fall, and one for winter).
 
It should also be able to store course information such as
* Course Name
* Time
* Section Code
* Location 
* Professor/Instructor
* Delivery Methods
* Term (F/S/Y)
* Faculty Offering

It needs to be able to search for course times and add (and remove) to the
timetable from a data source. It should have a user interface that is easy 
to use and understand for the user, while efficiently displaying all the 
useful information that the student needs.

## Scope
The scope of the design is a program that is able to display and plan for all 
the courses offered at the University of Toronto that are accounted for on 
the [Course Finder](https://coursefinder.utoronto.ca/course-search/search/courseSearch?viewId=CourseSearch-FormView&methodToCall=start).
There should also be some additional functions added to the tool, such as a 
`DfsSolver` to automatically plot out a timetable or an Agenda to keep track 
of all the student's course material. They shall be added on a case-by-case 
basis iff the time allows.
