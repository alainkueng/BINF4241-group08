# Assignment 4


## Home Automation

We chose to use the State Design Pattern, because of the ease of programming when dealing with many states of different objects. 
The Smartphone class acts as an Invoker, as described in the Command Design Pattern.
User class acts as Client which creates Commands and adds Devices. In our implementation
we aimed for a solution which is easily expandable. So a new Device can be added by creating such a Class and the respective Commands.
The user can interact with the app through the command line and has to use the provided commands in order to advance.
The main method is found in the User Class.