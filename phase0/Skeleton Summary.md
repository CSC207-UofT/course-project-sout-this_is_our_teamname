# Skeleton Program Summary
## Goal
We want the program to take input from the user and add an activity on a 
timetable.

##Process
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

10. After the course was added successfully. The User will be prompted on 
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


