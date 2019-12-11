import Source.Game;
import Source.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    /**
     * Scenario: A new Player object gets created with a specific name.
     * Testing if the getName() method returns the before entered string.
     */
    @Test
    void getName() {
        String playerName = "testOne";
        Player playerOne = new Player(playerName);
        assertEquals(playerName, playerOne.getName());
    }

    @Test
    void getScore() {
        Game game = new Game();
        String playerName = "testOne";
        Player player = new Player(playerName);
        game.addPlayer(player);
    }
}