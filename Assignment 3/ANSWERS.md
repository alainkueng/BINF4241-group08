# Assignment 3 ANSWERS
## Part 1

Design Pattern: Singleton

1. We chose to use the Singleton design pattern for our Board and Game initialization, since those should only be generated once. 
All figures are created more than once and therefore are not usable for Singleton. Both the Board and the Game class are created in 
a static initializer and are therefore thread safe. In the public getInstance() you get the one uniqueInstance returned.

2.
![Test Image 4](https://github.com/alainkueng/BINF4241-group08/blob/Dev/Assignment%203/Class%20Diagram%20Singleton.png)

 
 
 
Design Pattern Iterator
1. We decided to implement the Iterator design pattern to update the list of eaten pieces of both players. This way it
doesnt matter if we store the eaten pieces in an array/arraylist/list/etc it will always work. This improves the 
extensibility of the code. We implemented the iterator in our printBoard method in the Game class.


![Test Image 5](https://github.com/alainkueng/BINF4241-group08/blob/Dev/Assignment%203/Iterator%20sequence)










1. Why & How the two chosen patterns (Singleton, Observer, Adapter and Iterator) are implemented in our code

2. Class diagram of how the pattern is structured statically in our code

3. Sequence diagram of how the pattern works dynamically in our code

## Part 2
Consider the Board class implemented in your chess game. Draw its sequence diagram, considering all the method calls that can make it transition to a different state.  
Write a description of the sequence diagram (if needed) to further describe some of your choices. 

## Part 3
Implement a Scoreboard using the Observer pattern. The Scoreboard class must keep track of the score of each player. Each player scores a point when he/she eats an opponent piece and the Scoreboard must be updated accordingly.  
Note: each piece is worth 1 point, excluding the queen who's worth 5 points. Furthermore, the output of the game must be modified to show the players' score after each turn. For example, you should output a String similar to this: "Player 1, score: x - Player 2, score: y", 
where x and y are the scores of the players. Every time a piece is eaten the Board (or an equivalent class in your implementation) must notify the                  Scoreboard that something has changed. The Scoreboard must update itself accordingly. 
