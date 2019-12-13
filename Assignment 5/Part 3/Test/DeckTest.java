import Decks.PlayDeck;
import Source.*;
import Source.Game;
import Source.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Stack.*;

public class DeckTest {

    @Test
    public void playDeckRemoveAllForNewTest(){
        Game game = new Game();
        game.playDeck.removeAllForNew();
        assertEquals(game.playDeck.cards.size(),0);
    }
    @Test
    public void playDeckIsLastCard(){
        Game game = new Game();
        game.playDeck.removeAllForNew();
        Card card = new Card(CardColor.BLUE, CardType.NORMAL,2);
        game.playDeck.push(card);
        assertTrue(game.playDeck.isLastCard());
    }
}
