# Template Method Design Pattern
## Step 1:
### It solves the problem that two different components have significant similarities, but demonstrate no reuse of common interface or implementation, therefore, if a change common to both components becomes necessary, duplicate effort must be expended. It includes classes from two layers. One is a FrameworkClass, which is the base case and the large scale reuse infrastructure. The other is one or more ApplicationClass derived from the base class according to clients’ needs. The implementation of template_method() happens in FrameworkClass, and it is to define different functions. If clients need to reuse these codes or do some customization as needed, they can just create derived ApplicationClass from the base class and use or override the functions.
## Step 2:
### In our project, we created a class named TimeTableObject which is the base case of all the objects that can be placed in the timetables, such as Life, DescriptionlessLife and Section. These classes share a lot in common. Each of these objects should have functions to modify the information contained in it, to get the data stored in it and to output the information as strings respectively. For phase 0, we wrote the codes for these classes separately. This seems good during this phase as there is no such user-customizable objects. However, it can be a little bit inefficient now and in the future as we may plan to create more classes for TimetableObject to further specify the sections as needed. As a result, Template Method Design Pattern can be taken into consideration to reduce the workload as some base functions that each subclass of TimetableObject needs to contain can be carried in the base class. Then for each subclass, some related functions can be automatically generated to be directly used or to be ready for modification.
### Also, for my part of job in the project, I need to write codes for catching, identifying and responding to various exceptions that are thrown by Timetable. When dealing with these exceptions, similar functions need to be created and called. With the help of Template Method Design Pattern, all the base functions that are needed for processing the exceptions can be stored in a base framework. Common changes to the functions can be implemented in the base class, while modified functions can also be generated as needed in each application class.
### Next, from my point of view, for one of the most difficult tasks in our project, the graphical user interface can also benefit from Template Method Design Pattern. As far as I know, our GUI is planned to have the function to place all the sections that users select or create at the corresponding places in the timetable. Therefore, the GUI would implement several similar functions to objects from different TimetableObject classes, but there may still be slight differences as the GUI may want these different objects to be presented in different forms. When Template Method Design Pattern is introduced in the implementation of the GUI, the problem can be easily solved as FrameworkClass layer can help storing the base functions while ApplicationClass layer can help designating the process of different types of objects with adjusted functions.
### (https://en.wikipedia.org/wiki/Template_method_pattern)