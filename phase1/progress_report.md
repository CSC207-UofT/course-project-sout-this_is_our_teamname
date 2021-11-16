# Progress Report

## New (Non-Helper) Classes
* `Command Factory`
* `WebScraper`
* `InterfaceFacade` (Facade)
* `DataLoader`
* `DfsSolver`
* `TimeTablePuzzle`
* All the commands (there are a lot of them)

## Individual Contributions

### Matthew Du (dumatthe - matthew.du@mail.utoronto.ca)
* Seperated `CommandFactory` out of `DatabaseController`
* Created `Command` interface and all commands
* Cleaned up `CSVScraper` (from Phase 0)
* General cleanup of all classes

### Caules Ge (CaulesGe - caules.ge@mail.utoronto.ca)
* Created InterfaceFacade
* Created OperatorInterface
* Modified the UserInterface

### Aiden Lin (aiden-linhaoze - aiden.lin@mail.utoronto.ca)
* Created DataLoader
* Added methods to TimeTable
* Modified some of the Events subclasses

### Jennie Fang (fangjenn - jennie.fang@mail.utoronto.ca)
* Created TimeTablePuzzle 
* Looked for and cleaned Code Smells and Dependencies
* Cleaned up Events and Events subclasses

### Sonny Chen (sonnyffff - sonnyzijia.chen@mail.utoronto.ca)
* Created Webscraper
* Created WebscraperTest

### Hubert Gu (HubertGu - hubert.gu@mail.utoronto.ca)
* Implemented memento pattern
* Modified parameters of Events subclasses
* Created UndoCommand and RedoCommand