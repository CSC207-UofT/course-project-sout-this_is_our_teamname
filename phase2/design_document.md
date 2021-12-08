# Design Document
## Design Decision
* Create a new GUICommandFactory in LoadScreenHandler and SaveScreenHandler instead of using the factory gotten from getFactory() directly to make sure that the class input strictly satisfies the requirement of the original function.
