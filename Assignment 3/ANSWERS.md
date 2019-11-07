# Assignment 3 ANSWERS
## Part 1

####Singleton Design Pattern 

#####1. We chose to use the Singleton design pattern for our Board and Game initialization, since those should only be generated once. 
All figures are created more than once and therefore are not usable for Singleton. Both the Board and the Game class are created in 
a static initializer and are therefore thread safe. In the public getInstance() you get the one uniqueInstance returned.

#####2.
![Singleton pattern](https://github.com/alainkueng/BINF4241-group08/blob/Dev/Assignment%203/Class%20Diagram%20Singleton.png)

#####3.
 
 
 
####Iterator Design Pattern 
#####1. We decided to implement the Iterator design pattern to update the list of eaten pieces of both players. This way it
doesnt matter if we store the eaten pieces in an array/arraylist/list/etc it will always work. This improves the 
extensibility of the code. We implemented the iterator in our printBoard method in the Game class.

#####2.
![Iterator pattern](https://github.com/alainkueng/BINF4241-group08/blob/Dev/Assignment%203/Iterator%20diagram.png)

#####3.
![Iterator Sequence Diagram](https://github.com/alainkueng/BINF4241-group08/blob/Dev/Assignment%203/Iterator%20Sequence%20Diagram.png)








1. Why & How the two chosen patterns (Singleton, Observer, Adapter and Iterator) are implemented in our code

2. Class diagram of how the pattern is structured statically in our code

3. Sequence diagram of how the pattern works dynamically in our code

## Part 2
Consider the Board class implemented in your chess game. Draw its sequence diagram, considering all the method calls that can make it transition to a different state.  
Write a description of the sequence diagram (if needed) to further describe some of your choices. 

## Part 3
We implemented the Scoreboard using the _Observer_ pattern. We therefore created a Subject and Observer Interface
which are used by the Score Class (implements Subject) and Scoreboard Class (implements Observer). Score is keeping
track of all Observers through storing a list with all the registered Observers. In this case the only Observer is 
the Scoreboard. It gets notified after every turn through the Subject Score and checks in the update method if there
has been a change in the eatenPieces-list. If so, it updates the Scoreboard accordingly. However, in every case it
is responsible for printing the current score, even if it remains unchanged. We chose this design in order to
leave the "source code" mainly unchanged. The only change we made, was to create a Score and Scoreboard in our Game
Class as this class is the interface for the user interacting with the Application, and to notify the Observers
after each printout of the board. 