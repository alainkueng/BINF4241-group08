# Assignment 3 ANSWERS
## Part 1

#### Singleton Design Pattern 

##### 1. We chose to use the Singleton design pattern for our Board and Game initialization, since those should only be generated once. 
All figures are created more than once and therefore are not usable for Singleton. Both the Board and the Game class are created in 
a static initializer and are therefore thread safe. In the public getInstance() you get the one uniqueInstance returned.

##### 2.
[Singleton pattern](https://github.com/alainkueng/BINF4241-group08/blob/Test/Assignment%203/Class%20Diagram%20Singleton.png)

##### 3.
 
 
 
#### Iterator Design Pattern 
##### 1. We decided to implement the Iterator design pattern to update the list of eaten pieces of both players. This way it
doesnt matter if we store the eaten pieces in an array/arraylist/list/etc it will always work. This improves the 
extensibility of the code. We implemented the iterator in our printBoard method in the Game class.

##### 2.
[Iterator pattern](https://github.com/alainkueng/BINF4241-group08/blob/Test/Assignment%203/Iterator%20diagram.png)

##### 3.
[Iterator Sequence Diagram](https://github.com/alainkueng/BINF4241-group08/blob/Test/Assignment%203/Iterator%20Sequence%20Diagram.png)

## Part 2
For encapsulation reasons we decided that only the Board class can change its state itself. It gathers information from other classes and decides what action to take.

[Sequence Diagram for Board Class](https://github.com/alainkueng/BINF4241-group08/blob/Test/Assignment%203/Board%20Sequence%20Diagram.png)

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

# Further Information
## Changes

* Checkmate: We fixed the checkmate method
* Draw offer: Cosmetically changed, now it asks the other player with its name for a draw, if the currently playing
player asks for it. Additionally it is now possible to answer with a capital Y / N or lower y / n respectively.
* isPathFree: We fixed a minor bug, to check if a piece can move left down.
* If a player has no eaten pieces it now prints empty instead of nothing.
* Fixed a wrong print statement if someone did a castling move, now it's not showing "this move is invalid" anymore
(move was still done, although).
* Implemented a capture move with a pawn to the 1st or 8th row which also leads to a promotion, more on that in the
following section

## Notation
We follow the algebraic chess notation from _Wikipedia_. 

Additionally you can offer a draw with "(=)"

If a pawn makes a capture move on a piece which is on the last row of the opposite board side it gets also promoted.
The notation in such a case is as follows: "**gxh8=Q**" (*Pawn in column g makes a capture move to h8 and gets promoted to Queen*).
