# Progress Report

## Purpose Of The Project

As university students, every member of our group needs to have frequent access to 
a variety of timetable tools and scheduling apps. One of the most commonly used tools 
by us is the course timetable on Acorn. However, during our daily use of this tool, 
we find that there are several things about this tool that we are not satisfied enough 
due to the inconvenience or lack of efficiency. For example, when a student is lined up
in the waitlist of a course, it is still possible for him or her to enrol in courses
that even take place at the same time as the course in his or her waitlist, but when he
or she is removed from the waitlist to the enrolment list of that course automatically,
there will be a time conflict in his or her timetable on Acorn. Acorn will just leave
this conflict as it is without throwing any warnings to the student to inform this
conflict. As a result, our group decides to create a new timetable planning tool by using
our knowledge base on Java. This tool will enable users to check the information of UofT
courses according to a course database as well as schedule the courses they currently
enrol in. They can also make full use of this tool by customizing and putting personal
events in the timetable to plan their daily study and life.

## CRC model
We created following classes:
1. **Course**. An abstract class stores course sections, professor, location, time, delivery method, and faculty. 
It has 2 subclasses: **HCourse**, half-year course, and **YCourse**, year course.<br />
   <br />
2. **TimeTableObject** is an abstract class that can be stored in a TimeTable class. It holds an object for timetable. It has follow subclasses: **Section**, **Life**, and **DescriptionlessLife**.<br />
   <br />
3. **Section** is a subclass of  **TimeTableObject**. It stores startTime, endTime, location, date, term, code, professor, faculty, delivery method of the section.<br />
   <br />
4. **Life** is a subclass of  **TimeTableObject**, it holds time and description of activity.<br />
   <br />
5. **DescriptionlessLife** is a subclass of  **TimeTableObject**, it holds time of activity.<br />
   <br />
6. **TimeTable** has a map to store all the activities from Monday to Sunday with <day, ArrayList<**TimeTableObject**>> key-value pair. It collaborates with **TimeTableObject** to schedule the given activity into the appropriate weekday and check if there is a conflict in the timetable with given activity.<br />
   <br />
7. **TimeTableManager** collaborates with **Course**, **TimeTableObject**, and their subclasses. It manages and navigates through **TimeTable**, schedules the given **Course** and **TimeTableObject**, reports and responds to conflict, and discern term of course. <br />
   <br />
8. **DataGetter** is an abstract class. It collaborates with **UserInterface** and should have a **get** method that finds the information and returns it in a Map (Data Type: Data). Its subclasses are **WebScraper** and **CSVScraper**.<br />
   <br />
9. **WebScraper** is the subclass of **DataGetter**. It collaborates with **DatabaseController** and **OperatorInterface**. It reads the HTML of the UofT Course finder for the giver course and throws an Error when the given course is not found, analyze, filters the data, and stores the data as a Course Objects, and stores the course objects in a HashMap with section codes as keys and the courses as values.<br />
   <br />
10. **CSVScraper** is the subclass of **DataGetter**. It collaborates with **DatabaseController** and **OperatorInterface**. It reads a CSV file for the given course for information and throws an Error when the given course is not found, analyzes and filters the data and stores the data as **Course** Objects, and stores the **Course** objects in a HashMap with section codes as keys and the courses as values.<br />
    <br />
11. **UserInterface** collaborates with **TimeTableManager**. User interacts with it to input the course to search. It sends Input to **TimeTableManager** to interpret and deal with and displays final product to the user.<br />
    <br />
12. **DatabaseController** collaborates with **UserInterface** and **TimeTableManager**. It generates prompts based on the information received from **UserInterface** and sends objects to **TimeTableManager** to schedule.<br />
    <br />
13. **OperatorInterface** collaborates with **TimeTableManager**. Operator interacts with it, and it configures **TimeTableManager** and **DataGetter**.<br />
    <br />
14. **NonCourseObject** collaborates with **TimeTableManager** and **DatabaseController** Responsible for holding the information from the user and passing it to
    **TimeTableManager** to create individual **TimeTableObjects**.

(See [CRC_Cards_README](CRC_Cards/CRC_Cards_README.md))


## Scenario Walk-through and Skeleton Program Summary
### Goal
We want the program to take an input from the user and add an activity on a
timetable.

##Setup
The User interface asks the user to choose what type of activity the user
wants to add on to the timetable. The activities available right now are:
Course, Life, DescriptionlessLife.

####Case 1:  User inputs Course.
1. The `UserInterface` will send the input "Course" to the
   `DatabaseController`, which will prompt the user with questions about
   what they want to input. In this case, it will ask for what course
   User wants to input.<br />
   <br />
2. The `UserInterface` will send this information to the
   `DatabaseController`, which will prompt the User with questions about
   the course code they want to input.<br />
   <br />
3. The `DatabaseController` class then will request the data from the
   `CSVScraper` by using the `getData` method.<br />
   <br />
4. The `CSVScraper` then collects and sorts all the
   course information as a `HashMap<String, Course>` where each `Course`
   object only holds one section (LEC/TUT/PRA) and all the times and
   information relating to that one section. All the courses are then stored
   in a `HashMap<String, Course>`, where each key is the section name and
   the value is the `Course` object it found. The `Course` object will hold
   all the times of the lectures of the courses, as well as the locations of
   the classes at that time. Each course block will hold the Times and Locations
   as an `HashMap<ArrayList<Object>, String>`.<br />
   <br />
5. The `DatabaseController` retrieves the HashMap from the `CSVScraper` class
   and prompts the user to select a LEC/TUT/PRA of the course by printing each
   the details of the `Course`. The `DatabaseController` then sends the chosen
   to the `TimeTableManager` class through a `schedule` method. <br />
   <br />

6. The `TimeTableManager` will look for the term of the course which has
   been stored in the course object. The `TimeTableManager` will then use a
   `split` method of the `Course` object (since Course is splittable) to get an
   array of `section` objects that only stores one time interval of
   the chosen LEC/TUT/PRA.
   The `TimeTableManager` then will schedule the section in the appropriate
   `TimeTable` using a `schedule` method of `TimeTable`.<br />
   <br />

7. The `TimeTable` Object will then find the correct location to put the
   section in its storage space, which will be a `LinkedHashMap<String,
   ArrayList<String>>`, where the keys are the days of the week and the
   values are the `TimeTableObject`. Class `TimeTable` checks for conflicts
   in time for `Section` objects before scheduling, it is done so by using the
   Comparable interface implemented by 'Section'. For phase 0 anything with a
   conflict will not be scheduled. The confirmation that the object has been
   scheduled will be sent to `TimeTableManager`.<br />
   <br />

8. After the course was added successfully. The User will be prompted on
   whether he/she wants to add more object into his/hers timetable.<br />
   <br />

### Case 2: User inputs Life or DescriptionlessLife
**The process are the same for both, so we will use Life here for simplicity.**

1. The input `Life` will then be sent over to `DataBaseController` to be scheduled as an
   `NonCourseObject`. The User will then be prompted to enter all the information
   required for the Life Object.<br />
   <br />

2. The `DatabaseController` class will then create a `NonCourseObject`,
   which will store all the necessary information of the input, including
   the type of object. In this case, the `NonCourseObject` will store the
   date, start/end time, description, term, and the name of the activity, along
   with the type of object, which is a `Life` object<br />
   <br />

3. The `NonCourseObject` is then sent to the `TimeTableManager` through the
   `schedule` method. (Which is overloaded)<br />
   <br />

4. The `TimeTableManager` then will read the type of the `NonCourseObject`
   and create a `Life` Object. Then it will be sent to the`TimeTable` to be scheduled.<br />
   <br />

5. The `TimeTable` will then find the correct location to put the course in
   its storage space. (Note: Conflicts are only checked for `Section` Objects.)<br />
   <br />

6. After the `Life` object was added successfully. The User will be prompted on
   whether he/she wants to add more object into his/hers timetable.


## Future TODOs

* GUI
* Implement Operator Interface
* Implement Functions class
* Implement codes to catch invalid input exceptions
* Implement codes to store data by user account (if possible)
* Modify Life class for referring sessions
* Modify Descriptionlesslife class for further needs
* Add JavaDoc
* Better name for life, descriptionlesslife, NonCourseObject, etc.
* Change how conflicts are handled in the future.
* do something with waitlist courses.(Since we did mention it the purpose of the project)

## Difficulties That We Currently Face With (Suggestions Eagerly Needed)

* Lack of JavaDoc makes it difficult for quick communication.
* Frequent changing of the code structure costs great amount of time and effort.
* There is always a conflict between keeping adding features to make the program 
  better and the will of reducing workload even if it is just phase 0.
* Lack of a better time management plan between the project and school work. 
  Too much time spent on the project with too little done. Keeps getting interrupted by the project.
* Lack of communication sometimes results in duplicated work.
  Solution: Perhaps a google doc that everyone updates what they are working on in realtime?


## Questions to TA

1. Should we use Maps or Arrays when storing Timeblocks?
2. Are we allowed to use Dfssolver from CSC148? (also for Prof Calver, who was 
an instructor in CSC148H1 Winter 2021, source of code)

## Contribution Of Each Group Member

### Aiden Lin (linhaoz3 - aiden.lin@mail.utoronto.ca)

* Writing part of the progress report for phase 0
* Handling the implementation of coding for catching and reporting exceptions and conflicts in TimeTableManager class

### Caules Ge (gehongsh - caules.ge@mail.utoronto.ca)
* Writing the summary of CRC Model, Scenario walk-through and skeleton program in the progress report for phase 0
* The implementation of schedule method in TimeTableManager class

### Matthew Du (dumatthe - matthew.du@mail.utoronto.ca)
* Created classes:
  * DataGetter
  * CSVScraper
  * WebScraper (Trivial Implementation)
  * UserInterface
  * OperatorInterface (Trivial Implementation)
  * DatabaseController
  * GlobalHelperMethod
  * Constants (Trivial Implementation)
* Wrote Specifications
* Added specific details to Walk Through
* Transcribed CRC Cards from slides

### Sonny Chen (chenz347 - sonnyzijia.chen@mail.utoronto.ca)
* Created TimetableManager class
* Created TimetableManagerTest
* The implementation and update of schedule method in TimeTableManager class.

### Liyu Feng (Liquid - liyu.feng@mail.utoronto.ca)
* Helped created the bare-bone structure for TimeTable class
* Complete overhaul on TimeTable Class after architectural changes.
* Created TimeTableTest
* Fixed CompareTo interface.
* Created SectionTest to test the implementation of CompareTo in Section class.
* Updates on Progress report due to new architectural change.
* Phase 0 bug finding.

### Jennie Fang (jennie-f - jennie.fang@mail.utoronto.ca)
* Created TimeTableObject class
* Created Section class
* Created Life class
* Created Course class
* Helped create the Comparable and Sliceable interfaces
