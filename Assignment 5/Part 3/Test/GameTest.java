import Decks.PlayDeck;
import Source.Card;
import Source.Game;
import Source.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    Game game = new Game();

    /**
     * Tests if the card that the current player wants to play is a legal play
     */
    @Test
    public void testNumberCard(){
        PlayDeck playDeck = new PlayDeck();
        Card blueFive = new Card(Card.color.BLUE, Card.type.NORMAL,5);
        Card redFour = new Card(Card.color.RED, Card.type.NORMAL, 4);
        Card greenTwo = new Card(Card.color.GREEN, Card.type.NORMAL, 2);
        Card yellowFive = new Card(Card.color.YELLOW, Card.type.NORMAL, 5);


        playDeck.cards.push(blueFive);

        Assert.assertFalse(game.numberCard(redFour));
        Assert.assertTrue(game.numberCard(yellowFive));

        playDeck.cards.pop();
        playDeck.cards.push(greenTwo);
        //yellow on green with different numbers
        Assert.assertFalse(game.numberCard(yellowFive));
        //blue on green with different numbers
        Assert.assertFalse(game.numberCard(blueFive));
        //redFour on green with different numbers
        Assert.assertFalse(game.numberCard(redFour));
        //green on green with different numbers
        Assert.assertTrue(game.numberCard(greenTwo));
    }

    /**
     * Adds two players and checks if the next player is equal to the
     * second player
     *
     */
    @Test
    public void testGetNextPlayer(){
        Player p = new Player();
        Player r = new Player();
        game.addPlayer(p);
        game.addPlayer(r);
//        Player next = game.getNextPlayer();
        Assert.assertEquals(next,r);
    }
}
