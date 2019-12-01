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