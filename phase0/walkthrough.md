# Scenario Walk through
## Background
For this Scenario, just for fun, we will call the user "BillyBobJoe". 
Consider he is trying to plan for his timetable. He is trying to enroll in 
the following courses:
* MAT257Y1
* CSC207H1
* CSC240H1
* STA257H1
* NMC468H1

## The Walk through
1. BillyBobJoe searches for the first course, which happens to be `MAT257Y1` in 
   UserInterface.
2. The UserInterface class requests the data from the DataGathering class which 
   refers to Web Scraping by default. **TODO Fix this if taking the 
   `OperatorInterface` Idea**
3. The DataGathering then gets and stores the data (LEC/TUT/PRA) from the 
   datasource and returns a HashMap of all the information in the form of
   `{Section Name: Course Object}` **TODO Change to Array? Faster Runtime?**. 
4. UserInterface retrieves the data from the Web Scraping and prompts the 
   user to select a section (which section, what time, etc.)
5. The `UserInterface` class then sends the selected section to 
   TimeTableManager which schedules the course in the correct timetable 
   (S/F/both). 
   1. The timetable will schedule a course into the correct slot, which will be 
      either a Course or Life object. In this case, it will be a course object, since it 
      is MAT133Y1. 
   2. The timetable will be modified accordingly.
6. BillyBobJoe will then receive a confirmation on UserInterface that the 
   course has been scheduled. He then can add the next course or life 
   object into his timetable until he is happy!
