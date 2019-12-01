# Assignment 5 ANSWERS

Please view this md-file on the Github website since it's not properly shown in IntelliJ

## Part 1

#### Subtitle

##### 1.


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

#### Behavior of UNO-implementation

##### 1. Inputs that should be accepted:

* To start an _integer_ should be provided by the user in order to create the players. At this stage it shouldn't accept
any inputs of a different type like _floats_, _strings_, _chars_ etc. The minimum amount is 2 and the maximum is 10.

* Then it should ask for the player name's, otherwise it gets hard that everyone can remember their player number if
there are 10 people. The input should be of type _string_, others are not accepted. Also it should cap the amount of
characters, because this could be exploited.

* While the game is on, the players should be able to input the color, number, of a card or the identifier for an
action card (e.g. "Yellow 5", "Green 3" or "Red Draw 2", "Wild Draw 4 Blue", "Yellow Reverse", "Blue Skip", "Wild Red").
The color provided after a wild card defines the next color to play.
A player can of course also draw a card, which will be done with the "Draw" input. If the input is not recognizable,
the game should ask for a retry.

* If a player has only two cards and wants to play one of them it is possible that he/she inputs "UNO" first, before
declaring his turn, this is the only time it is possible, that a player can give two inputs but the first must be
"UNO" otherwise, you only have the possibility to provide one input (if the input is correct and you're not asked
to retry as stated above).

##### 2. What should happen with the inputs:

* The _integer_ which is provided by the user should set the amount of player-object that should be created (It
should set the upper bound for the loop which will ask the player to enter a name for each player).

* This _string_ which serves as a name should then set the attribute "name" of the respective Player object.

* When a player is playing a card by giving the correct input, the application should parse this input in order to know 
which card has been selected by the player. It then checks from the **existing** cards being present in the hand of the 
respective player. If the player indeed holds such a card the application checks if due to the game's rules it is
possible to play this card after the last played one. If it is correct, the application should remove it and add it to 
the played cards stack, otherwise the player will be asked to play another card which he/she actually holds.
Naturally if the card 