# Design Document

## UML Diagram
* [The Diagram](https://docs.google.com/presentation/d/1Y4G68pZL0exLt_WOKD81JUoqP-viaEZpGGOaLyKMd6M/edit#slide=id.p)
* [Presentation](https://docs.google.com/presentation/d/1WdHyNQxlsQ0VAOPu0ujWFXbWTQDfYz4l92REhfZY83A/edit?usp=sharing)

## SOLID
### How we adhered to the SOLID principles
Here are some examples of how we adhered to the SOLID principles!
* **Single Responsibility Principle**: Our UserInterface and Operator 
  Interfaces only interact with one type of users only, and is only responsible for 
  that type. In fact, we are using a Facade! Therefore, it is adhering to SDP
* **Open/Closed Principle:** Our Command Factory is an example of the 
  Open/Closed Principle. It is closed, as everything is private and cannot be modified, 
  but it can be open for extension of new commands
* **Listov Substitution Principle:** For our Event classes, each one of them 
  can be substituted for one of their child classes.
* **Interface Segregation Principle:** The DatabaseController is an example of 
  the ISP. The controller is an barrier between the user and the command 
  factory, and purpose is to make sure that each command is doing one task 
  and one task only
* **Dependency Inversion Principle:** An example of the Dependency Inversion 
  Principle is the Events Class. The event class acts as a barrier for the 
  upper and lower classes.

### Weaknesses
We openly admit the following design flaws in our program. We will fix these in phase 2 
* Dependency Principle not adhered to in DataLoader 
* Unclear relations between each two of UserInterface, DatabaseController, 
  Command Factory and Operator Interface

## Code Organization
* We decided to organize our code using a hybrid of Dependency layers and 
  Components.
  * For instance, we grouped classes like CommandFactory and DatabaseController 
    in one package, but we grouped all the commands in another!

## Design Patterns
* Command and Factory Pattern hybrid for creating command objects
* InterfaceFacade class created using facade pattern to encapsulate  
  UserInterface and OperatorInterface
* Memento Pattern for undo user action
* Strategy Pattern for Solver

## Refactoring
* Code Smells: Fixed the long parameter lists for Events classes 
  * Easier to read and understand
* Created more meaningful class names for Events classes 
  * TimeTableObject -> Events 
  * Section -> CourseSection 
  * Life -> Activity 
  * DescriptionlessLife -> Task

## Difficulties we are facing
* As the project size grew, it is getting more difficult to maintain a clean 
  architecture. We are facing problems with packaging, code smell, SOLID 
  principles. Even what was we thought was a good idea became ill thought 
  out once the project grew. 
* Not enough test cases proved to be nearly disastrous for pinpointing the 
  bug. Using the debugger through half the program was not fun.

