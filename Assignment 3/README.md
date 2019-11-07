# Assignment 3 README

Reused code from Chess game in Assignment 2.

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

For ANSWERS.md file please refer to:

[ANSWERS.md](https://github.com/alainkueng/BINF4241-group08/blob/Test/Assignment%203/ANSWERS.md)
