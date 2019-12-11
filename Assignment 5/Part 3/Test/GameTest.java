import Cards.WildDraw4Test;
import Decks.PlayDeck;
import Source.*;
import Source.Game;
import Source.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game = new Game();

    /**
     * Tests if the card that the current player wants to play is a legal play
     */
    @Test
    public void testNumberCardOnNumberCard(){
        PlayDeck playDeck = new PlayDeck();
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card redFour = new Card(CardColor.RED, CardType.NORMAL, 4);
        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL, 2);
        Card yellowFive = new Card(CardColor.YELLOW, CardType.NORMAL, 5);


        playDeck.cards.push(blueFive);

        //red on blue with different numbers
       assertFalse(game.numberCard(redFour));
        //yellow on blue with same number
        assertTrue(game.numberCard(yellowFive));

        playDeck.cards.pop();
        playDeck.cards.push(greenTwo);
        //yellow on green with different numbers
        assertFalse(game.numberCard(yellowFive));
        //blue on green with different numbers
        assertFalse(game.numberCard(blueFive));
        //redFour on green with different numbers
        assertFalse(game.numberCard(redFour));
        //green on green with different numbers
        assertTrue(game.numberCard(greenTwo));

    }

    /**
     * Scenario: After a draw four wildcard was played and the chosen color: green
     * Testing if the normal card played immediately after is a legal play
     */
    @Test
    public void testNumberCardAfterWildCardDrawFour(){
        PlayDeck playDeck = new PlayDeck();

        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL, 2);
        Card yellowFive = new Card(CardColor.YELLOW, CardType.NORMAL, 5);
        Card wildPlusFour = new Card(CardColor.BLACK, CardType.WILD_D4, 50);

        playDeck.cards.pop();
        playDeck.cards.push(wildPlusFour);
        // yellow card on wildPlusFour with chosen color: green
        assertFalse(game.numberCard(yellowFive));
        // green card on wildPlusFour with chosen color: green
        assertTrue(game.numberCard(greenTwo));

    }

    /**
     * Scenario: After a draw two wildcard of color blue was played
     * Test if the next normal card of the next player is a legal play
     */
    @Test
    public void testNumberCardAfterPlusTwoCard(){
        PlayDeck playDeck = new PlayDeck();
        Card bluePlusTwo = new Card(CardColor.BLUE, CardType.DRAW_2,20);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card redFour = new Card(CardColor.RED, CardType.NORMAL, 4);
        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL, 2);
        Card yellowFive = new Card(CardColor.YELLOW, CardType.NORMAL, 5);
        playDeck.cards.push(bluePlusTwo);
        //blue card on a blue draw 2 card
        assertTrue(game.numberCard(blueFive));
        //red card on a blue draw 2 card
        assertFalse(game.numberCard(redFour));
    }

    /**
     * Scenario: After a wildcard was played and the color blue was chosen
     * Test if the next normal card of the next player is a legal play
     */
    public void testNumberCardAfterWildCard(){
        PlayDeck playDeck = new PlayDeck();
        Card wild = new Card(CardColor.BLACK, CardType.WILD,50);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card redFour = new Card(CardColor.RED, CardType.NORMAL, 4);
        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL, 2);
        Card yellowFive = new Card(CardColor.YELLOW, CardType.NORMAL, 5);

        playDeck.cards.push(wild);

        //green card on wild card of color blue
        assertFalse(game.numberCard(greenTwo));
        //blue card on wild card of color blue
        assertTrue(game.numberCard(blueFive));




    }
    /**
     * Adds two players and checks if the next player is equal to the
     * second player
     */
    @Test
    public void testGetNextPlayer(){
        Player p = new Player("p");
        Player r = new Player("r");
        game.addPlayer(p);
        game.addPlayer(r);
        Player next = game.getNextPlayer();
        assertEquals(next,r);
    }
}
