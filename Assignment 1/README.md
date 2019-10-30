# Snakes and Ladders
#### Snakes and Ladders is a boardgame, that allows up to 4 players to play simultaneously


## Rules
#### 1. Two players can never be in the same square, except in the first one. The last player to enter an occupied square gets sent back to square 1.
#### 2. If a player enters a square that has a ladder or a snake, he/she gets moved to wherever this ladder or snake is pointing towards in the gameboard.
#### 3. A player wins if he/she reaches the last square.
#### 4. If a player is close to the last square and dices a bigger number than what what is needed to win, he/she then goes to the last square and then moves back the remaining number of moves.




## How it works
#### 1. The game asks the user for:
   * Height & Width of the gameboard
   
   * Number of players
   
   * Name of these players

#### 2. Game then generates the gameboard with random snakes and ladders scattered across the gameboard. However, before creating the snakes and ladders, the game checks if the boardsize is big enough(bigger than 6) to even implement snakes and ladders in the gameboard.

#### 3. A snake and a ladder cant start or end on the same square.
#### 4. After initialization, the game cycles through the players until one of them wins.



### Lonely?

#### There are people who dont have any friends and would still like to play so we implemented that possibility.
