# CRC Cards.

In this file, please find our CRC cards. It is categorized into 4 folders:
* DataCollection
  * [_CSVScraper](DataCollection/_CSVScraper.md)
  * [DataGetter](DataCollection/DataGetter.md)
  * [WebScrapper](DataCollection/WebScraper.md)
* TimeTableObject
  * CourseStuff
    * [Course](TimeTableObjects/CourseStuff/Course.md)
    * [HCourse](TimeTableObjects/CourseStuff/HCourse.md)
    * [YCourse](TimeTableObjects/CourseStuff/YCourse.md)
  * [Life](TimeTableObjects/Life.md)
  * [TimeTableObject](TimeTableObjects/TimeTableObject.md)
* TimeTableStuff
  * [TimeTable](TimeTableStuff/TimeTable.md)
  * [TimeTableManager](TimeTableStuff/TimeTableManager.md)
* UserInterface
  * [UserInterface](UserInterface/UserInterface.md)
  * [_OperatorInterface](UserInterface/_OperatorInterface.md)

## UserInterface
The classes listed under here are the classes that the user and the operators
interact with. Under the dependency rule, this would fall under the
**Frameworks and Drivers** ring.

## DataCollection
The classes listed under here are the classes that gathers the 
data/information from data sources. Under the dependency rule, this would 
fall under the **Frameworks and Drivers** ring.

## TimeTableStuff
The classes listed under here are the main classes that controls and 
manipulates the timetables, including the timetable itself. 
Under the dependency rule,
* The `TimeTable` class falls under the **Application Business Rules (Use 
  Cases)** 
* The `TimeTableManager` class falls under the **Interface Adapters** ring

## TimeTableObject
The classes listed under here are the classes that holds the course data and 
other timetable objects. Under the dependency rule, this would fall under 
the **Enterprise Business Rules (Entities)** ring.
