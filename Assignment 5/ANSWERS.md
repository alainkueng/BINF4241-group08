# Assignment 5 ANSWERS

Please view this md-file on the Github website since it's not properly shown in IntelliJ

## Part 1

#### Testing Snakes and Ladders

-First off we decided to move the game logic out of the Game() constructer. To do so, we made an extra method that is called run() in the Game class, so we don't have to play a Game everytime we create a new Game class in the test package. run() will now start the game. In addition we deleted the main class since it's useless ands solely for starting the Game.run(). Because of this we moved the public static void main to the Game class. You can now run the Game in the Game class.
We also redesigned the addPlayer method. It's four methods now:
1. Renamed addPlayer() to addPlayerToBoard() and removed some logic to other methods.
2. Added checkNumberStringForAddPlayer() that checks how many players you want to add.
3. Added checkPlayerString() that checks the input name for possible errors (Integer, empty,..).
4. Added compareNames() that checks if the player name is already used.

Plus we added readInput() that solely purpose is reading the commandLine input. In addition to that we redesigned createBoard() into two methods:
1. Changed createBoard() that now requires to Integers as Input.
2. Added checkNumberStringForCreateBoard() that checks if the given input string is an integer.

All this changes were made that we can better test our code, they were just moved in other methods, not changed. Now we don't have to input strings while running a test. We didn't write tests for: 
- readInput() since it's only java.scanner code. 
- run() method since it just executes all the methods in the game in the right order.
- all getters and setters since they just have one line of code (return).
- printGame() since it just reflects all the results of the executed code.
- Lastsquare.class since it's a square.class with one changed boolean.

##### 1. Think of how and what would break the code
- Possible wrong inputs from the user that would crash the game
- Edge cases that the game can't handle
- Infinite loops

##### 2. Document all test cases
- All the test cases are documented with javadoc in the "tests" package that you find in part 1. Each test can be started on its own.

##### 3. How you found the bug and how you fixed it
- There was a bug in the old addPlayer() method were you could add the same name for every player, we found it while writing the test. We fixed it now in the compareNames() that checks the list of players properly and returns a boolean. 
- From our first meeting with the tutor we know that we coded the createBoard() in such a way that you could create huge boards that the memory can't handle. We restricted it to 10000 fields max. and 2 min.
- In addition to the first bug, you could enter an empty string or "spaces" as names. This is now restricted as we used the java matches function from strings in the checkPlayerString() method.
- We found a bug while testing that you can add a negative number for the boardsize in checkNumberStringForCreateBoard() that crashes the game. We fixed it that you can only write integers and no "-".

## Part 2
### GameBoardTest
#### MarkOnBoard(): 
- The if statement, in the mark method, to check whether a position in the gameboard was already taken or not returned true when the position was already taken. This is wrong as it has to return false when the position is already taken(!= null) and return true when the position is free and add the player to that position.
#### GetOpenPositionsAll():
- The for loop with the columns started at 1 instead of 0. We fixed this by initializing col = 0.

### TicTacToeGameState
#### StartingPlayerisX(): 
- The getCurrentPlayer was always returning player.O because currentPlayer was being set to player.0 in the method. We changed the return to currentPlayer directly.

#### GetAvailableStatesLastOne(): 
- This test method was failing because the getCurrentPlayer always returned player.O .

#### IsOverWin():
- This test method did not pass because the method completesDiagonal() which was called by hasWin(), had the wrong indexes when checking for the diagonal from the upper left to the lower right.

## Part 3

#### 1. Behavior of UNO-implementation

##### a. Inputs that should be accepted:

* To start an _integer_ should be provided by the user in order to create the players. At this stage it shouldn't accept
any inputs of a different type like _floats_, _strings_, _chars_ etc. The minimum amount is 2 and the maximum is 10.

* Then it should ask for the player name's, otherwise it gets hard that everyone can remember their player number if
there are 10 people. The input should be of type _string_, others are not accepted. Also it should cap the amount of
characters, because this could be exploited.

* While the game is on, the players should be able to input the color, number, of a card or the identifier for an
action card (e.g. "Yellow 5", "Green 3" or "Red Draw 2", "Wild Draw 4 Blue", "Yellow Reverse", "Blue Skip", "Wild Red").
The color provided after a wild card defines the next color to play. Enemy players can claim a missing "UNO"-call or
a illegal "Wild Draw 4" by providing the input "Claim Wild Draw 4" or "Claim UNO".
A player can of course also draw a card, which will be done with the "Draw" input. If the input is not recognizable,
the game should ask for a retry.

* If a player has only two cards and wants to play one of them it is possible that he/she inputs "UNO" first, before
declaring his turn, this is the only time it is possible, that a player can give two inputs but the first must be
"UNO" otherwise, you only have the possibility to provide one input (if the input is correct and you're not asked
to retry as stated above).

##### b. What should happen with the inputs:

* The _integer_ which is provided by the user should set the amount of player-object that should be created (It
should set the upper bound for the loop which will ask the player to enter a name for each player).

* This _string_ which serves as a name should then set the attribute "name" of the respective Player object.

* When a player is playing a card by giving the correct input, the application should parse this input in order to know 
which card has been selected by the player. It then checks from the **existing** cards being present in the hand of the 
respective player, if the player indeed holds such a card the application checks if due to the game's rules it is
possible to play this card after the last played one. If it is correct, the application should remove it and add it to 
the played cards stack, otherwise the player will be asked to play another card which he/she actually holds.
After playing a "Wild Draw 4" or a "Draw 2" card, the next player automatically gets the respective amount of cards and 
their turn will be skipped. The same happens for a "Skip"-card. In this case these cards trigger an action to change
the game's routine and/or trigger a draw-loop until the defined amount of cards has been drawn by the next player. The
input should only trigger changes to the current players hand, upon this follows changes to the card stack and if it was
an action card changes to the game routine or the next players hand. If the player enters an invalid input not 
compatible with the notation, the turn is rejected hand has to be redone again.

* Each turn there should be a check if the player holds only 2 cards. In this case the player's first input should be
"UNO", if this doesn't happen any other player will be able to claim "UNO" which will add two cards to the player who
has missed his/her "UNO" call. However this input has to happen before another player takes his/her turn, otherwise the
missed "UNO" is compliant. The application should keep track of this possibilities until the time frame is expired due
to the above mentioned reason.

* UNO doesn't have a unlimited card deck, therefore if a player tries to draw a card, but there is none left, the played
card stack will be reshuffled and used as a new card deck. This action will be triggered automatically after a player
draws a card and the card deck is empty. After each draw command the card deck itself checks if it is empty, in this
case it will take the played card stack except the last added one, shuffle it and reuse the shuffled cards as new card
deck.

##### c. Expected outputs:

* The application should ask for the amount of players and their names afterwards.

* It should also output the cards each player has in his/her hand.

* When the game starts, it should give a corresponding feedback.

* Whenever the game is running it should output the last card played, or the first drawn card.

* If an input isn't recognizable, the application should provide this feedback and ask for a retry.

* The game should output how many cards enemy players hold, but shouldn't reveal which cards are being held.

* When calling "UNO" the game should provide feedback, that this is legal and is understood, so no one can claim 
"UNO" afterwards.

* After playing his last card, the application should announce the winner and end the game. This should only happen
if the turn was legal and all rules were followed.

#### 2.![Class_Diagramm](https://github.com/alainkueng/BINF4241-group08/blob/Dev/Assignment%205/Class_Diagram.png)
#### 3.
