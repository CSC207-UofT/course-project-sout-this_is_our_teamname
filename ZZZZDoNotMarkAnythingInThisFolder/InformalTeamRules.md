# Team Rules (Informal)
Due to the onslaught of mayhem that occurred on October 11, I have created 
this rules document to prevent bloody chaos in the future. Seriously. Feel 
free to add or remove as you desire from this document.

## General Rules
1. Please make sure everything you merge into the main branch on GitHub runs 
   **without errors** from IntelliJ. Warnings and TODOs are fine but please 
   make sure it can run.
   * If you would like to push code that is incomplete or requires a method 
     or class that has not been completed, please comment that line out
   * If you don't know how to complete a method with a return statement, 
     please return a trivial solution and mark a `TODO` beside it. For instance,
```java
public class AClass{
    public boolean boolReturner(){
        // TODO IDK HOW TO IMPLEMENT THIS!
        return false;
        // Do this even if the return may not be false. This is so there isn't an error raised from missing a body
    }
}  
```

## Pull Requests
_Note: team member approving something refers to them commenting something 
like "Approved" on your pull request_
1. If it is something new and unrelated to other files, you can merge it 
   yourself. Such as a typo, brand new test class, etc
2. If you need something from a class that someone else is assigned to work 
  on, you may modify it at will. However, only the person who wrote that 
  module may merge it to the main branch. The other person may only make a 
  pull request. For instance, if team member A wants to add a method in class 
  Course written by team member B, they are able to freely. However, team member
  A may not merge the file on GitHub. Person A must put a Pull Request and 
  Person B will accept or refuse that pull request. 
   * **Exception 1**: You may merge it if the person responsible does not 
     respond to communications after 24h
   * **Exception 2**: The due date is in <= 24h. Then, anyone can merge any 
     changes, given there are no conflicts.
3. If you need to make a change to the design of anything (class, method, 
   CRC Cards even), at least half the group must approve.
