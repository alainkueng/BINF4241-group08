import Source.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    /**
     * Scenario: A new Player object gets created with a specific name.<br>
     * Testing if the getName() method returns the before entered string.
     */
    @Test
    void getName() {
        String playerName = "testOne";
        Player playerOne = new Player(playerName);
        assertEquals(playerName, playerOne.getName());
    }

    /**
     * Scenario: A player holds certain cards which is specified in the test.<br>
     * Testing if the getScore() method calculates the correct score and returns it afterwards.
     */
    @Test
    void getScore() {
        Game game = new Game();
        String playerName = "test";
        game.addPlayer(playerName);
        Player player = game.players.get(0);
        player.addCard(new Card(CardColor.BLUE, CardType.NORMAL, 2));
        player.addCard(new Card(CardColor.RED, CardType.NORMAL, 8));
        player.addCard(new Card(CardColor.BLACK, CardType.WILD, 50));
        player.addCard(new Card(CardColor.BLACK, CardType.SKIP, 20));
        assertEquals(80, player.getScore());
    }
}