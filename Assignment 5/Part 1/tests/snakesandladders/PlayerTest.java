package snakesandladders;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    /**
     * Scenario: The player roles the dice and moves for that amount.<br>
     * Tests: This method tests if the move() function from Game() works. First we simulate a run(). Then we move the player<br>
     * to next corresponding square with the move function and check if the squareList now contains the Name and the<br>
     * player knows on what square he is.
     */
    @Test
    public void MoveTest() {
        Game game = new Game();
        game.createBoard(1,6);
        game.addPlayerToBoard("TestName");
        game.numPlayer = 1;
        Player current_player = game.players.get(game.current);
        int to_move = game.checkNumber(5, current_player); //to be sure that it isn't a snake or a ladder
        current_player.move(to_move);
        assertTrue(game.squares.get(5).player_list.get(0).name == "TestName");
        assertTrue(game.players.get(0).square.position == 6);
    }
}
