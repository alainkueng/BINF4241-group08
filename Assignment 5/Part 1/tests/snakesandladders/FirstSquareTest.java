package snakesandladders;
import org.junit.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FirstSquareTest {

    /**
     * Scenario: The first player wants to start playing. leave() gets as input the player that should leave the first<br>
     * square and removes the player from it's square.<br>
     * Before the test two players will spawn at the firstSquare. Then the first Player moves.<br>
     * Tests: The assert tests if the firstSquare.playerList is size of 1 since one got removed.<br>
     * Then the next Player moves.<br>
     * The second assert Tests if the FirstSquare is now empty after both left.
     */
    @Test
    public void leaveTest(){
        Game game = new Game();
        game.createBoard(6,1);
        game.addPlayerToBoard("TestName");
        game.addPlayerToBoard("TestName1");
        //run() simulated
        game.numPlayer = 2;
        Player current_player = game.players.get(game.current);
        int to_move = game.checkNumber(5, current_player); //to be sure that it isn't a snake or a ladder
        current_player.move(to_move);
        game.squares.get(0).leave(current_player);
        assertTrue(game.squares.get(0).player_list.size() == 1);
        game.nextPlayer();
        Player current_player2 = game.players.get(game.current);
        game.squares.get(0).leave(current_player2);
        assertTrue(game.squares.get(0).player_list.size() == 0);
    }

    /**
     * Scenario: The player wants to move to a field that's already occupied, therefore he gets moved back to the first<br>
     * square. The first square should never be occupied.<br>
     * Tests: This method tests if the FirstSquare is occupied. Since we implemented that this square can have as many<br>
     * Players on it as wanted, the FirstSquare will always say it's not occupied (i.e. has space for more Players)
     */
    @Test
    public void isOccupiedTest() {
        Game game = new Game();
        game.createBoard(6,1);
        game.addPlayerToBoard("TestName");
        game.addPlayerToBoard("TestName1");
        assertFalse(game.squares.get(0).isOccupied()); //Should always be false
    }
}
