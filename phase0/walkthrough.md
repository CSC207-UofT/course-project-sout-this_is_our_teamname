# Scenario Walk through
## Background
For this Scenario, just for fun, we will call the user "BillyBobJoe". 
Consider he is trying to plan for his timetable. He is trying to enroll in 
the course `MAT257Y1`

Let's say that `MAT257Y1` has sections:
* LEC0101 MWF 2-3
* LEC0102 MWF 3-4
* TUT0101 T 3-5
* TUT0102 R 3-5

(If you don't know, M = Monday, T = Tuesday, W = Wednesday, R = Thursday, 
F = Friday and assume all times are PM for simplicity)

## The Walk through
1. Before BillyBobJoe even searches for a course, an Operator must first use 
   the OperatorInterface class and set the source of data. UserInterfaces 
   will have an instance variable `operator` that is referring to the 
   operator. The operator can use `configure` method to configure the 
   `dataSource` instance variable of `UserInterface` to the correct object 
   of the DataGetter class. **For the purpose of the Phase 0, this is 
   will not be implemented, and the reader automatically set to CSVReader, but 
   for the rest of the project, we hope to use WebScraper.**
2. BillyBobJoe searches for the first course, which happens to be `MAT257Y1` in 
   UserInterface class. He will first select in the UserInterface that he is 
   scheduling a Course Object and not a life object. (See the Walk Through 2 
   below for scheduling a Life object.) He then will type the course code in 
   full in the search menu.
3. The `UserInterface` class then will request the data from the DataGathering 
   class by creating a DataGathering object. The query is then sent to the 
   DataGathering class which was set by the `OperatorInterface`. The user 
   **cannot** change this setting.
4. The DataGathering class (whatever it is) then collects and sorts all the
   course information as a HashMap of course objects **where each `Course` 
   object only holds one section (LEC/TUT/PRA) and all the times and 
   information relating to that one section. The course objects (HCourse or 
   YCourse) will be created based off of the terms for the course read from 
   the source. All the courses are then stored in a `HashMap<String, 
   Course>`**, where each key is the **section name** and the value is the 
   **course object**. For instance, for our `MAT257Y1` course then the 
   HashMap would look like the following:
```
{LEC0101: <the YCourse object for LEC0101>, LEC0102: <the YCourse object for 
LEC0102>, TUT0101: <the YCourse object for TUT0101>, TUT0102: <the YCourse 
object for TUT0102>}
```
   The course object will hold **all** the times of the lectures of the 
   courses, as well as **the locations of the classes at that time**. For 
   `MAT257Y1`, each course block will hold the Times and Locations as an 
   `HashMap<String, String>`. For our example above, say all three times of 
   LEC0101 occur at room MY150 (why not?). Then, the YCourse object will 
   hold the following in terms of timeLocation (Note, we are just being 
   hand-wavy here. The formatting of the strings may not be as follows, but 
   the idea should be the same):
```
HashMap<String, String> timeLocation = {Monday_2 - 3: MY150, Wednesday_2 - 3: 
MY150, Friday_2 - 3: MY150}
```
   This is because for some courses, they occur at different locations at 
   different times.
5. The `UserInterface` retrieves the HashMap from the DataGathering class and 
   prompts the user to select a course object by printing each key and 
   perhaps details of the section obtained from the values. The user then 
   **types** the course into the command line (at least for Phase 0) to 
   "commit" their choice. The UserInterface then **sends the course object 
   at the value of the course section that the user selected to the 
   'TimeTableManager'** class. For this example, lets say BillyBobJoe 
   chooses LEC0101, then the UserInterface will look up in the HashMap the 
   value at "LEC0101" **and send that to the TimeTableManager through a 
   `schedule` method**. The `Schedule` method here **Should be Overloaded to 
   be able to deal with both Course objects and TimeTableObjects'** see Walk 
   Through 2 for more details on this.
    
   **NOTE THE HASHMAP OF COURSES SHOULD NOT DELETED AFTER 
   THIS STEP**. This is so if the timetable is unable to schedule the course,
   the user then would be prompted to choose another from the HashMap.
7. The `TimeTableManager` will look for the term of the course **which has 
   been stored in the course object**. It will then use a **`split` method 
   in the course object to get an array of `section` objects that only 
   stores one time and one location of a course**. In the example of 
   `MAT257Y1`, we would have a **section array of 6 elements**:
   1. Section with time and date stored as MONDAY 2 - 3 for FALL 
   2. Section with time and date stored as WEDNESDAY 2 - 3 for FALL
   3. Section with time and date stored as FRIDAY 2 - 3 for FALL
   4. Section with time and date stored as MONDAY 2 - 3 for WINTER
   5. Section with time and date stored as WEDNESDAY 2 - 3 for WINTER
   6. Section with time and date stored as FRIDAY 2 - 3 for WINTER
   
   **NOTE THIS IS 6 ELEMENTS BECAUSE THIS IS A YEAR COURSE. IF THIS WAS A HALF 
   COURSE (HCourse) THERE WILL ONLY BE 3 OR SECTIONS.**
   The `TimeTableManager` then **sends this information to the correct 
timetable based on the term stored from above** by sending it through the 
   **`Schedule` Method of Class TimeTable**.
8. The **TimeTable Object** will then find the correct location to put the 
   course in its storage space, which will be a `HashMap<String, String>`, 
   where the keys are the time and **the values are the `TimeTableObject`s**.
   **Class `TimeTable`** will then send a confirmation that it has scheduled the 
   course properly **to `TimeTableManager`**, which will pass the information to 
   the `UserInterface` to inform them that the course has been scheduled 
   properly. If it hasn't been scheduled successfully, the TimeTable will 
   inform the TimeTableManager, which will inform the user that it has not 
   been successful and that they should try again. **For simplicity in Phase 
   0, we will assume all courses are scheduled correctly and there are no 
   conflicts**
9. Let's say the course was added successfully. BillyBobJoe will then receive a 
   confirmation on UserInterface that the course has been scheduled. He then 
   can add the next course or life object into his timetable.

## Walk Through 2
Now let's say BillyBobJoe wants to schedule a Life object. For this example, 
let's say he wants to schedule an hour on Wednesday to play the Theremin 
with the Toronto Theremin Orchestra (IDK if this is real).
1. First, he would select that he wants to add a life object on the 
   UserInterface. This is done (at least in Phase 0) by typing 'Life' into 
   the command line when prompted.
2. He is then asked to select a time and description. So for this example, 
   he would type:
```
Please select the time of your life object: Wednesday, 5-6
Please write a brief description of your life object: Theremin Orchestra
```
   The UserInterface class will then create a life object from the given 
   information and send it to the TimeTableManager through the `Schedule` 
   method. **ONCE AGAIN, PLEASE NOTE THAT THE `schedule` METHOD IN 
   `TimeTableManager` IS OVERLOADED**.
3. The `TimeTableManager` then **sends this information to the correct 
   timetable based on the term stored from above** by sending it through the 
   **`Schedule` Method of Class TimeTable**. 
4. The **TimeTable Object** will then find the correct location to put the 
   course in its storage space, which will be a `HashMap<String, String>`, 
   where the keys are the time and **the values are the `TimeTableObject`s**.
   **Class `TimeTable`** will then send a confirmation that it has scheduled 
   the life object properly **to `TimeTableManager`**, which will pass the 
   information to the `UserInterface` to inform them that the life object has 
   been scheduled properly. If it hasn't been scheduled successfully, the 
   TimeTable will inform the TimeTableManager, which will inform the user 
   that it has not been successful and that they should try again. **For 
   simplicity in Phase 0, we will assume all objects are scheduled correctly 
   and there are no conflicts**
5. Let's say the life object was added successfully. BillyBobJoe will then 
   receive a confirmation on UserInterface that the life has been scheduled. He 
   then can add the next course or life object into his timetable.
