package snakesandladders;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
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
    }
}
