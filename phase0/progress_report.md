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

## Summary Of Current Progress

##CRC model
We created following classes:
1. **Course**. An abstract class stores course sections, professor, location, time, delivery method, and faculty. 
It has 2 subclasses: **HCourse**, half-year course, and **YCourse**, year course.
2. **TimeTableObject** is an abstract class that can be stored in a TimeTable class. It holds an object for timetable. It has follow subclasses: **Section**, **Life**, and **DescriptionlessLife**.
3. **Section** is a subclass of  **TimeTableObject**. It stores startTime, endTime, location, date, term, code, professor, faculty, delivery method of the section.
4. **Life** is a subclass of  **TimeTableObject**, it holds time and description of activity.
5. **DescriptionlessLife** is a subclass of  **TimeTableObject**, it holds time of activity.
6. **TimeTable** has a map to store all the activities from Monday to Sunday with <day, ArrayList<**TimeTableObject**>> key-value pair. It collaborates with **TimeTableObject** to schedule the given activity into the appropriate weekday and check if there is a conflict in the timetable with given activity.
7. **TimeTableManager** collaborates with **Course**, **TimeTableObject**, and their subclasses. It manages and navigates through **TimeTable**, schedules the given **Course** and **TimeTableObject**, reports and responds to conflict, and discern term of course. 
8. **DataGetter** is an abstract class. It collaborates with **UserInterface** and should have a **get** method that finds the information and returns it in a Map (Data Type: Data). Its subclasses are **WebScraper** and **CSVScraper**.
9. **WebScraper** is the subclass of **DataGetter**. It collaborates with **DatabaseController** and **OperatorInterface**. It reads the HTML of the UofT Course finder for the giver course and throws an Error when the given course is not found, analyze, filters the data, and stores the data as a Course Objects, and stores the course objects in a HashMap with section codes as keys and the courses as values.
10. **CSVScraper** is the subclass of **DataGetter**. It collaborates with **DatabaseController** and **OperatorInterface**. It reads a CSV file for the given course for information and throws an Error when the given course is not found, analyzes and filters the data and stores the data as **Course** Objects, and stores the **Course** objects in a HashMap with section codes as keys and the courses as values.
11. **UserInterface** collaborates with **TimeTableManager**. User interacts with it to input the course to search. It sends Input to **TimeTableManager** to interpret and deal with and displays final product to the user.
12. **DatabaseController** collaborates with **UserInterface** and **TimeTableManager**. It generates prompts based on the information received from **UserInterface** and sends objects to **TimeTableManager** to schedule.
13. **OperatorInterface** collaborates with **TimeTableManager**. Operator interacts with it, and it configures **TimeTableManager** and **DataGetter**.

## Scenario walk-through
1. Before user searches a course,  an operator uses the OperatorInterface class and set the source of data and  UserInterfaces will refer to the operator. The operator can use `configure` method to configure the
   `dataSource` instance variable of `UserInterface` to the correct object
   of the DataGetter class.
2. If user searches for a course, he needs to select to schedule a Course Object in the UserInterface.
3. **UserInterface** will request the data from the DataGathering
   class set by the instance `dataSource`. The query is then sent to the
   DataGathering class which was set by the `OperatorInterface`.
4. The DataGathering class (whatever it is) then collects and sorts all the course information as a **HashMap<String, Course>**,the key is the **section name** and the value is **course object**. The Course object will hold all the times of the lectures of the courses, as well as the locations of the classes at that time.
5. The **UserInterface** retrieves the HashMap from the DataGathering class and prompts the user to select a course object by printing each key and perhaps details of the section obtained from the values. The user then **types** the course into the command line (at least for Phase 0) to "commit" their choice. The UserInterface then **sends the course object at the value of the course section that the user selected to the 'TimeTableManager' class**.
6. The **TimeTableManager** will look for the term of the course **which has been stored in the course object**. The TimeTableManager will then use a **`Split` method OF the Course object to get an array of Section objects that only stores one time and one location of a course**.
7. The **TimeTable Object** will then find the correct location to put the
   course in its storage space, which will be a `HashMap<String, String>`,
   where the keys are the time and **the values are the `TimeTableObjects`**.
   **Class `TimeTable`** will then send a confirmation that it has scheduled the
   course properly **to `TimeTableManager`**, which will pass the information to
   the `UserInterface` to inform them that the course has been scheduled
   properly. If it hasn't been scheduled successfully, the TimeTable will
   inform the TimeTableManager, which will inform the user that it has not
   been successful and that they should try again. 
8. If the course is added successfully, the user will receive a
   confirmation on UserInterface that the course has been scheduled. He then
   can add the next course or life object into his timetable.

## Walk Through2

If the user wants to schedule a **Life** object, he selects to add a **Life** object on the UserInterface by typing 'Life' into the command line when prompted. Then he will be required to select a time and description.

The UserInterface class will then create a life object from the given
information and send it to the TimeTableManager through the `Schedule`
method.

The `TimeTableManager` then **sends this information to the correct
timetable based on the term stored from above** by sending it through the
**`Schedule` Method of TimeTable Class**.

The **TimeTable Object** will find the correct location to put the course in its storage space.
**Class `TimeTable`** will then send a confirmation that it has scheduled
the life object properly **to `TimeTableManager`**, which will pass the
information to the `UserInterface` to inform them that the life object has
been scheduled properly. If failed, the
TimeTable will inform the TimeTableManager, which will inform the user
that it has not been successful and that they should try again.

If successfully, the user will receive a confirmation on UserInterface that the course has been scheduled. He then can add the next course or life object into his timetable.


## Future TODOs

* GUI
* Implement Operateor Interface
* Implement Functions class
* Implement codes to catch invalid input exceptions
* Implement codes to store data by user account (if possible)
* Modify Life class for refurring sessions
* Modify Descriptionlesslife class for further needs
* Add JavaDoc

## Difficulties That We Currently Face With (Suggestions Eagerly Needed)

* Lack of JavaDoc makes it difficult for quick communication.
* Frequent changing of the code structure costs great amount of time and effort.
* There is always a conflict between keeping adding features to make the program better and the will of reducing workload even if it is just phase 0.

## Contribution Of Each Group Member

### Aiden Lin (linhaoz3 - aiden.lin@mail.utoronto.ca)

* Writing part of the progress report for phase 0
* Handling the implementation of coding for catching and reporting exceptions and conflicts in TimeTableManager class
