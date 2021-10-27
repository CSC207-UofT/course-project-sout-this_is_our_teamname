# Scenario Walk through
## Background
For this Scenario, just for fun, we will call the user "BillyBobJoe". 
Consider he is trying to plan for his timetable. He is trying to enroll in 
the course `MAT257Y1`

Let's say that `MAT257Y1` has sections:
* LEC0101 MWF 2-3 @ MY150
* LEC0102 MWF 3-4 @ MY150
* TUT0101 T 3-5 @ MY380
* TUT0102 R 3-5 @ MY380

(Where, M = Monday, T = Tuesday, W = Wednesday, R = Thursday, F = Friday and 
assume all times are PM for simplicity)

## The Walk through
1. Before BillyBobJoe even searches for a course, an Operator must first use 
   the OperatorInterface class and set the source of data. UserInterfaces 
   will have an instance variable `operator` that is referring to the 
   operator. The operator can use `configure` method to configure the 
   `dataSource` instance variable of `Interfaces` to the correct object 
   of the DataGetter class. **For the purpose of the Phase 0, this is 
   will not be implemented. The reader automatically set to `CSVReader` for 
   this part, but for the rest of the project, we hope to use primarily 
   WebScraper.**<br />
   <br />

2. BillyBobJoe searches for the first course, which is `MAT257Y1` in 
   Interfaces class. He will first be prompted to select the type 
   of object that he wants to schedule. So he will enter `Course`. (See the 
   Walk Through 2 below for scheduling a `NonCourseObject`.)<br />
   <br />

3. The `Interfaces` will send this information to the 
   `DatabaseController`, which will prompt the user with questions about 
   what they want to input. In this case, it will ask for what course 
   BillyBobJoe wants to input, which he then enters `MAT257Y1`.<br />
   <br />

4. The `DatabaseController` class then will request the data from the 
   DataGathering class by using the instance `getData` method. The query is 
   then sent to the `DataGathering` class (whatever it is).<br />
   <br />

5. The DataGathering class (whatever it is) then collects and sorts all the
   course information as a `HashMap<String, Course>` where each `Course` 
   object only holds one section (LEC/TUT/PRA) and all the times and 
   information relating to that one section. All the courses are then stored 
   in a `HashMap<String, Course>`, where each key is the section name and 
   the value is the `Course` object it found. For instance, for our `MAT257Y1` 
   course then the HashMap would look like the following:
```
{LEC0101: <the Course object for LEC0101>, LEC0102: <the Course object for LEC0102>, TUT0101: <the Course object for TUT0101>, TUT0102: <the Course object for TUT0102>}
```
   The `Course` object will hold all the times of the lectures of the 
   courses, as well as the locations of the classes at that time. For 
   `MAT257Y1`, each course block will hold the Times and Locations as an 
   `HashMap<ArrayList<Object>, String>`. In our example, the Course object 
   will hold the following in terms of timeLocation (Note, we are just being 
   hand-wavy here. The formatting of the strings may not be as follows, but 
   the idea should be the same):
```
HashMap<ArrayList<Object>, String> timeLocation = {
new ArrayList<Object>{"Monday", Time(14, 0, 0), Time(15, 0, 0)}: MY150, 
new ArrayList<Object>{"Wednesday", Time(14, 0, 0), Time(15, 0, 0)}: MY150, 
new ArrayList<Object>{"Friday", Time(14, 0, 0), Time(15, 0, 0)}: MY150
}
```
   This is because for some courses, they occur at different locations at 
   different times.  <br />
   <br />

6. The `DatabaseController` retrieves the HashMap from the `DataGathering` class 
   and prompts the user to select a course object by printing each the details 
   of the `Course`. The user then types the course into the command line (at 
   least for Phase 0) to "commit" their choice. The `DatabaseController` then 
   sends the course object at the value of the course section that the user 
   selected to the `TimeTableManager` class. For this example, 
   `DatabaseController` will send the LEC 0101 `Course` object to 
   `TimeTableManager` through a `schedule` method. The `Schedule` method 
   here is Overloaded to be able to deal with both `course` objects and 
   `NonCourseObjects` (see Walk Through 2 for more details on this).<br />
   <br />
    
7. The `TimeTableManager` will look for the term of the course which has 
   been stored in the course object. The `TimeTableManager` will then use a 
   `split` method of the `Course` object (since Course is splittable) to get an 
   array of `section` objects that only stores one time and one location of 
   a course**. In the example of `MAT257Y1`, we would have a `section` array 
   of 6 elements:
   1. Section with time and date stored as MONDAY 2 - 3 for FALL 
   2. Section with time and date stored as WEDNESDAY 2 - 3 for FALL
   3. Section with time and date stored as FRIDAY 2 - 3 for FALL
   4. Section with time and date stored as MONDAY 2 - 3 for WINTER
   5. Section with time and date stored as WEDNESDAY 2 - 3 for WINTER
   6. Section with time and date stored as FRIDAY 2 - 3 for WINTER
   
   **NOTE THIS IS 6 ELEMENTS BECAUSE THIS IS A YEAR COURSE. IF THIS WAS A HALF 
   COURSE (HCourse) THERE WILL ONLY BE 3 OR SECTIONS.**
   The `TimeTableManager` then will schedule the section in the appropriate 
   `TimeTable` using a `schedule` method of `TimeTable`. This method behaves 
   like the `put` or `add` methods in HashMap and ArrayList respectively.<br />
   <br />

8. The `TimeTable` Object will then find the correct location to put the 
   course in its storage space, which will be a `HashMap<String, 
   ArrayList<String>>`, where the keys are the days of the week and the 
   values are the `TimeTableObject`. Class `TimeTable` will then send a 
   confirmation that it has scheduled the course properly to 
   `TimeTableManager` to inform the user that the course has been scheduled 
   properly. If it hasn't been scheduled successfully, the TimeTable will 
   inform the `TimeTableManager`, which will inform the user that it has not 
   been successful and that they should try again. **For simplicity in Phase 
   0, we will assume all courses are scheduled correctly and there are no 
   conflicts**<br />
   <br />

9. Let's say the course was added successfully. BillyBobJoe will then receive a 
   confirmation on `Interfaces` that the course has been scheduled. He then 
   can add the next object into his timetable.<br />
   <br />

## Walk Through 2
Now let's say BillyBobJoe wants to schedule a Life object. For this example, 
let's say he wants to schedule an hour on Wednesday to play the Theremin 
with the Toronto Theremin Orchestra (IDK if this is real).
1. First, he would select that he wants to add a `Life` object on the 
   Interfaces. This is done (at least in Phase 0) by typing 'Life' into 
   the command line when prompted.<br />
   <br />

2. This will then be sent over to `DataBaseController` to be scheduled as an 
   `NonCourseObject`. He will then be prompted to enter all the information 
   required for the Life Object.<br />
   <br />

3. The `DatabaseController` class will then create a `NonCourseObject`, 
   which will store all the necessary information of the input, including 
   the type of object. In this case, the `NonCourseObject` will store the 
   date, start/end time, description, term, and the name of the activity, along 
   with the type of object, which is a `Life` object<br />
   <br />

4. The `NonCourseObject` is then sent to the `TimeTableManager` through the 
   `schedule` method. **ONCE AGAIN, PLEASE NOTE THAT THE `schedule` METHOD IN 
   `TimeTableManager` IS OVERLOADED**.<br />
   <br />

5. The `TimeTableManager` then will read the type of the `NonCourseObject` 
   and create the appropriate `TimeTableObject` to be scheduled. In this 
   case, it will read a Life object and create a `Life` Object. The correct 
   `TimeTableObject` (in this case, a life object) will then be sent to the 
   `TimeTable` to be scheduled.<br />
   <br />

6. The `TimeTable` will then find the correct location to put the course in 
   its storage space. Please see Part 1 for details on how this done.<br />
   <br />

7. Let's say the `Life` object was added successfully. BillyBobJoe will then 
   receive a confirmation on Interfaces that the life has been scheduled. He 
   then can add the next course or life object into his timetable.
