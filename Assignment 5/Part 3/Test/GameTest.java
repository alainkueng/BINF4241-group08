import Decks.PlayDeck;
import Source.*;
import Source.Game;
import Source.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game = new Game();

    /**
     * Tests if the normal card that the current player wants to play is a valid play <br>
     *     and if the numberCard method pushes the card to the deck.
     */
    @Test
    public void testNumberCardOnNumberCard(){
        PlayDeck playDeck = new PlayDeck();

        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card redFive = new Card(CardColor.RED, CardType.NORMAL, 5);

        playDeck.push(blueFive);
        assertTrue(game.validPlayCheck(redFive));
        game.numberCard(redFive);
        assertEquals(redFive,playDeck.pop());
    }

    /**
     * Scenario: After a draw four wildcard was played and the chosen color = green <br>
     *     Testing if the normal card played immediately after is a valid play.
     */
    @Test
    public void testNumberCardAfterWildCardDrawFour(){
        PlayDeck playDeck = new PlayDeck();

        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL, 2);
        Card yellowFive = new Card(CardColor.YELLOW, CardType.NORMAL, 5);
        Card wildPlusFour = new Card(CardColor.BLACK, CardType.WILD_D4, 50);

        playDeck.push(wildPlusFour);
        // yellow card on wildPlusFour with chosen color: green
        assertFalse(game.validPlayCheck(yellowFive));
        // green card on wildPlusFour with chosen color: green
        assertTrue(game.validPlayCheck(greenTwo));
        game.numberCard(greenTwo);
        assertEquals(greenTwo,playDeck.pop());
    }

    /**
     * Scenario: After a draw two wildcard of color blue was played <br>
     *     Test if the next normal card of the next player is a legal play.
     */
    @Test
    public void testNumberCardAfterPlusTwoCard(){
        PlayDeck playDeck = new PlayDeck();
        Card bluePlusTwo = new Card(CardColor.BLUE, CardType.DRAW_2,20);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card redFour = new Card(CardColor.RED, CardType.NORMAL, 4);

        playDeck.push(bluePlusTwo);
        //red card on a blue draw 2 card
        assertFalse(game.validPlayCheck(redFour));
        //blue card on a blue draw 2 card
        assertTrue(game.validPlayCheck(blueFive));
        game.numberCard(blueFive);
        assertEquals(blueFive,playDeck.pop());
    }

    /**
     * Scenario: After a wildcard was played and the color blue was chosen <br>
     * Test if the next normal card of the next player is a legal play
     */
    @Test
    public void testNumberCardAfterWildCard(){
        PlayDeck playDeck = new PlayDeck();
        Card wild = new Card(CardColor.BLACK, CardType.WILD,50);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL, 2);

        playDeck.push(wild);


        //green card on wild card of color blue
        assertFalse(game.validPlayCheck(greenTwo));
        //blue card on wild card of color blue
        assertTrue(game.validPlayCheck(blueFive));
        game.numberCard(blueFive);
        assertEquals(blueFive,playDeck.pop());
    }
    /**
     * Scenario: After a skip card was played <br>
     *      Test if the next normal card of the next player is a legal play
     */
    @Test
    public void testNumberCardAfterSkipCard(){
        PlayDeck playDeck = new PlayDeck();
        Card blueTwo = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card skip = new Card(CardColor.BLUE, CardType.SKIP, 20);
        playDeck.push(skip);

        //blue card on blue skip card
        assertTrue(game.validPlayCheck(blueTwo));
        game.numberCard(blueTwo);
        assertEquals(blueTwo,playDeck.pop());
    }

    /**
     * Scenario: After a reverse card was played <br>
     *      Test if the next normal card of the next player is a legal play
     */
    @Test
    public void testNumberCardAfterReverse(){
        PlayDeck playDeck = new PlayDeck();
        Card blueNine = new Card(CardColor.BLUE, CardType.NORMAL,9);
        Card reverse = new Card(CardColor.GREEN, CardType.REVERSE,20);

        playDeck.push(reverse);

        //blue card on green reverse card
        assertTrue(game.validPlayCheck(blueNine));
        game.numberCard(blueNine);
        assertEquals(blueNine,playDeck.pop());
    }
    /**
     * Adds two players to the game, sets current player to the first player of the list and checks if <br>
     *     the getNextPlayer method returns the second player of the list.
     */
    @Test
    public void testGetNextPlayer(){
        game.addPlayer("p");
        game.addPlayer("r");
        Player next = game.getNextPlayer();
        assertEquals(next,game.currentPlayer);
    }


    /**
     * Scenario: After any card <br>
     * Test if a wild card is accepted as the next card in the game
     */
    @Test
    public void testWildCard(){
        PlayDeck playDeck = new PlayDeck();
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card bluePlusTwo = new Card(CardColor.BLUE, CardType.DRAW_2,20);
        Card wildPlusFour = new Card(CardColor.BLACK, CardType.WILD_D4, 50);

        Card wild = new Card(CardColor.BLACK, CardType.WILD,50);
        Card skip = new Card(CardColor.BLUE, CardType.SKIP, 20);
        Card reverse = new Card(CardColor.GREEN, CardType.REVERSE,20);



        playDeck.push(blueFive);
        //wild card draw four after normal card
        assertTrue(game.validPlayCheck(wild));
        game.wild(wild);
        assertEquals(wild, playDeck.pop());

        playDeck.pop();
        playDeck.push(bluePlusTwo);
        //wild card after draw two card
        assertTrue(game.validPlayCheck(wild));
        game.wild(wild);
        assertEquals(wild, playDeck.pop());


        playDeck.cards.pop();
        playDeck.cards.push(wildPlusFour);
        //wild card after wild plus four
        assertTrue(game.validPlayCheck(wild));
        assertEquals(wild, playDeck.pop());

        playDeck.cards.pop();
        playDeck.cards.push(skip);
        //wild card after skip
        assertTrue(game.validPlayCheck(wild));
        assertEquals(wild, playDeck.pop());

        playDeck.cards.pop();
        playDeck.cards.push(reverse);
        //wild card after reverse
        assertTrue(game.validPlayCheck(wild));
        assertEquals(wild, playDeck.pop());

        playDeck.cards.pop();
        playDeck.cards.push(wildPlusFour);
        //wild card after wild card
        assertTrue(game.validPlayCheck(wild));
        assertEquals(wild, playDeck.pop());
    }

    /**
     * Scenario: After any card <br>
     * Test if the draw four card is accepted
     */
    @Test
    public void testWildDrawFour(){
        PlayDeck playDeck = new PlayDeck();
        Card wildPlusFour = new Card(CardColor.BLACK, CardType.WILD_D4, 50);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card wild = new Card(CardColor.BLACK, CardType.WILD,50);
        Card skip = new Card(CardColor.BLUE, CardType.SKIP, 20);
        Card reverse = new Card(CardColor.GREEN, CardType.REVERSE,20);

        //plus four wild card after normal





    }

    /**
     * Scenario: After any card <br>
     * Test if the draw two card is accepted
     */
    @Test
    public void testDrawTwo(){
        PlayDeck playDeck = new PlayDeck();
        Card bluePlusTwo = new Card(CardColor.BLUE, CardType.DRAW_2,20);

    }

    /**
     * Scenario: After any card <br>
     * Test if the skip card is accepted
     */
    @Test
    public void testSkip(){
        PlayDeck playDeck = new PlayDeck();
        Card skip = new Card(CardColor.BLUE, CardType.SKIP, 20);
    }

    /**
     * Scenario: After any card <br>
     * Test if the reverse card is accepted
     */
    @Test
    public void testReverse(){
        PlayDeck playDeck = new PlayDeck();
        Card reverse = new Card(CardColor.GREEN, CardType.REVERSE,20);

    }
}
