import Decks.PlayDeck;
import Source.*;
import Source.Game;
import Source.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Stack.*;

public class GameTest {
    Game game = new Game();
    

    /**
     * Tests if the normal card that the current player wants to play is a valid play <br>
     *     and if the numberCard method pushes the card to the deck.
     */
    @Test
    public void testNumberCardOnNumberCard(){
        game.playDeck = new PlayDeck();

        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card redFive = new Card(CardColor.RED, CardType.NORMAL, 5);

        game.playDeck.push(blueFive);
        assertTrue(game.validPlayCheck(redFive));
        game.numberCard(redFive);
        assertEquals(redFive,game.playDeck.pop());
    }

    /**
     * Scenario: After a draw four wildcard was played and the chosen color = green <br>
     *     Testing if the normal card played immediately after is a valid play.
     */
    @Test
    public void testNumberCardAfterWildCardDrawFour(){
        game.playDeck = new PlayDeck();

        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL, 2);
        Card yellowFive = new Card(CardColor.YELLOW, CardType.NORMAL, 5);
        Card wildPlusFour = new Card(CardColor.BLACK, CardType.WILD_D4, 50);

        game.playDeck.push(wildPlusFour);
        // yellow card on wildPlusFour with chosen color: green
        assertFalse(game.validPlayCheck(yellowFive));
        // green card on wildPlusFour with chosen color: green
        assertTrue(game.validPlayCheck(greenTwo));
        game.numberCard(greenTwo);
        assertEquals(greenTwo,game.playDeck.pop());
    }

    /**
     * Scenario: After a draw two wildcard of color blue was played <br>
     *     Test if the next normal card of the next player is a legal play.
     */
    @Test
    public void testNumberCardAfterPlusTwoCard(){
        game.playDeck = new PlayDeck();
        Card bluePlusTwo = new Card(CardColor.BLUE, CardType.DRAW_2,20);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card redFour = new Card(CardColor.RED, CardType.NORMAL, 4);

        game.playDeck.push(bluePlusTwo);
        //red card on a blue draw 2 card
        assertFalse(game.validPlayCheck(redFour));
        //blue card on a blue draw 2 card
        assertTrue(game.validPlayCheck(blueFive));
        game.numberCard(blueFive);
        assertEquals(blueFive,game.playDeck.pop());
    }

    /**
     * Scenario: After a wildcard was played and the color blue was chosen <br>
     * Test if the next normal card of the next player is a legal play
     */
    @Test
    public void testNumberCardAfterWildCard(){
        game.playDeck = new PlayDeck();
        Card wild = new Card(CardColor.BLACK, CardType.WILD,50);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL, 2);

        game.playDeck.push(wild);


        //green card on wild card of color blue
        assertFalse(game.validPlayCheck(greenTwo));
        //blue card on wild card of color blue
        assertTrue(game.validPlayCheck(blueFive));
        game.numberCard(blueFive);
        assertEquals(blueFive,game.playDeck.pop());
    }
    /**
     * Scenario: After a skip card was played <br>
     *      Test if the next normal card of the next player is a legal play
     */
    @Test
    public void testNumberCardAfterSkipCard(){
        game.playDeck = new PlayDeck();
        Card blueTwo = new Card(CardColor.BLUE, CardType.NORMAL,2);
        Card skip = new Card(CardColor.BLUE, CardType.SKIP, 20);
        game.playDeck.push(skip);

        //blue card on blue skip card
        assertTrue(game.validPlayCheck(blueTwo));
        game.numberCard(blueTwo);
        assertEquals(blueTwo,game.playDeck.pop());
    }

    /**
     * Scenario: After a reverse card was played <br>
     *      Test if the next normal card of the next player is a legal play
     */
    @Test
    public void testNumberCardAfterReverse(){
        game.playDeck = new PlayDeck();
        Card blueNine = new Card(CardColor.BLUE, CardType.NORMAL,9);
        Card reverse = new Card(CardColor.GREEN, CardType.REVERSE,20);

        game.playDeck.push(reverse);

        //blue card on green reverse card
        assertTrue(game.validPlayCheck(blueNine));
        game.numberCard(blueNine);
        assertEquals(blueNine,game.playDeck.pop());
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
        game.playDeck = new PlayDeck();
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card bluePlusTwo = new Card(CardColor.BLUE, CardType.DRAW_2,20);
        Card wildPlusFour = new Card(CardColor.BLACK, CardType.WILD_D4, 50);

        Card wild = new Card(CardColor.BLACK, CardType.WILD,50);
        Card skip = new Card(CardColor.BLUE, CardType.SKIP, 20);
        Card reverse = new Card(CardColor.GREEN, CardType.REVERSE,20);



        game.playDeck.push(blueFive);
        //wild card draw four after normal card
        assertTrue(game.validPlayCheck(wild));
        game.wild(wild);
        assertEquals(wild, game.playDeck.pop());

        game.playDeck.pop();
        game.playDeck.push(bluePlusTwo);
        //wild card after draw two card
        assertTrue(game.validPlayCheck(wild));
        game.wild(wild);
        assertEquals(wild, game.playDeck.pop());


        game.playDeck.cards.pop();
        game.playDeck.cards.push(wildPlusFour);
        //wild card after wild plus four
        assertTrue(game.validPlayCheck(wild));
        assertEquals(wild, game.playDeck.pop());

        game.playDeck.cards.pop();
        game.playDeck.cards.push(skip);
        //wild card after skip
        assertTrue(game.validPlayCheck(wild));
        assertEquals(wild, game.playDeck.pop());

        game.playDeck.cards.pop();
        game.playDeck.cards.push(reverse);
        //wild card after reverse
        assertTrue(game.validPlayCheck(wild));
        assertEquals(wild, game.playDeck.pop());

        game.playDeck.cards.pop();
        game.playDeck.cards.push(wildPlusFour);
        //wild card after wild card
        assertTrue(game.validPlayCheck(wild));
        assertEquals(wild, game.playDeck.pop());
    }

    /**
     * Scenario: After any card <br>
     * Test if the draw four card is accepted
     */
    @Test
    public void testWildDrawFour(){
        game.playDeck = new PlayDeck();
        Card wildPlusFour = new Card(CardColor.BLACK, CardType.WILD_D4, 50);
        Card wildPlusFourTwo = new Card(CardColor.BLACK, CardType.WILD_D4, 50);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card wild = new Card(CardColor.BLACK, CardType.WILD,50);
        Card skip = new Card(CardColor.BLUE, CardType.SKIP, 20);
        Card reverse = new Card(CardColor.GREEN, CardType.REVERSE,20);

        //plus four wild card after normal

        game.playDeck.push(blueFive);
        game.wildDrawFour(wildPlusFour);
        assertTrue(game.validPlayCheck(wildPlusFour));
        assertEquals(wildPlusFour, game.playDeck.pop());

        //plus four wild card after wild
        game.playDeck.push(wild);
        game.wildDrawFour(wildPlusFour);
        assertTrue(game.validPlayCheck(wildPlusFour));
        assertEquals(wildPlusFour, game.playDeck.pop());

        //plus four wild card after skip
        game.playDeck.push(skip);
        game.wildDrawFour(wildPlusFour);
        assertTrue(game.validPlayCheck(wildPlusFour));
        assertEquals(wildPlusFour, game.playDeck.pop());

        //plus four wild card after reverse
        game.playDeck.push(reverse);
        game.wildDrawFour(wildPlusFour);
        assertTrue(game.validPlayCheck(wildPlusFour));
        assertEquals(wildPlusFour, game.playDeck.pop());

        //plus four wild card after reverse
        game.playDeck.push(reverse);
        game.wildDrawFour(wildPlusFour);
        assertTrue(game.validPlayCheck(wildPlusFour));
        assertEquals(wildPlusFour, game.playDeck.pop());

        //plus four wild card after plus four wild card
        game.playDeck.push(wildPlusFourTwo);
        game.wildDrawFour(wildPlusFour);
        assertTrue(game.validPlayCheck(wildPlusFourTwo));
        assertEquals(wildPlusFourTwo, game.playDeck.pop());
    }

    /**
     * Scenario: After any card <br>
     * Test if the draw two card is an accepted play and if the drawTwo method places the card on the deck.
     */
    @Test
    public void testDrawTwo(){
        game.playDeck = new PlayDeck();
        Card greenPlusTwo = new Card(CardColor.GREEN, CardType.DRAW_2,20);
        Card greenOne = new Card(CardColor.GREEN, CardType.NORMAL,1);
        Card bluePlusTwo = new Card(CardColor.BLUE, CardType.DRAW_2, 20);

        game.playDeck.push(greenOne);
        assertTrue(game.validPlayCheck(greenPlusTwo));
        game.drawTwo(greenPlusTwo);
        assertEquals(greenPlusTwo, game.playDeck.pop());


        //plus two on plus two
        assertTrue(game.validPlayCheck(bluePlusTwo));
        game.drawTwo(bluePlusTwo);
        assertEquals(bluePlusTwo,game.playDeck.pop());

    }

    /**
     * Scenario: After any card <br>
     * Test if the skip card is an accepted play and if the skip method places the skip card on the deck.
     */
    @Test
    public void testSkip(){
        game.playDeck = new PlayDeck();
        Card skip = new Card(CardColor.YELLOW, CardType.SKIP, 20);
        Card yellowSix = new Card(CardColor.YELLOW, CardType.NORMAL, 6);

        game.playDeck.push(yellowSix);
        assertTrue(game.validPlayCheck(skip));
        game.skip(skip);
        assertEquals(skip,game.playDeck.pop());
    }

    /**
     * Scenario: After any card <br>
     * Test if the reverse card is an accepted play and if the reverse method places the reverse card on the deck
     */

    @Test
    public void testReverse(){
        game.playDeck = new PlayDeck();
        Card reverse = new Card(CardColor.GREEN, CardType.REVERSE,20);
        Card redTwo = new Card(CardColor.RED, CardType.NORMAL, 2);

        game.playDeck.push(redTwo);
        assertTrue(game.validPlayCheck(reverse));
        game.reverse(reverse);
        assertEquals(reverse,game.playDeck.pop());


    }

    /**
     * Scenario: The draw deck is empty, so we take the play deck except the last card, reshuffle it and store <br>
     *     as the draw deck. <br>
     *     Test to see if the size of the new draw deck equals the size of the old played deck - 1.
     */
    @Test
    public void testShuffle(){
        game.playDeck = new PlayDeck();
        int size = game.playDeck.cards.size() - 1;
        game.drawDeck = game.reshuffle();

        //play deck only consists of the last card on the stack after the reshuffle
        assertEquals(game.playDeck.cards.size(),1);
        //test if the new draw deck size is equal to the size of the past playdeck - 1;
        assertEquals(game.drawDeck.cards.size(), size);
    }

    @Test
    public void testCheckForUno(){
        Player p = new Player("Ramon");
        Player a = new Player("Raffi");

        Card blueTwo = new Card(CardColor.BLUE, CardType.NORMAL,2);
        Card greenTwo = new Card(CardColor.GREEN, CardType.NORMAL,2);
        Card blueFive = new Card(CardColor.BLUE, CardType.NORMAL,5);
        Card yellowSix = new Card(CardColor.YELLOW, CardType.NORMAL, 6);



        p.addCard(blueTwo);
        p.addCard(blueFive);

        a.addCard(yellowSix);

        //player p calls uno but still has >= 1 cards
        p.setUno();
        assertFalse(game.checkForUno());

        //player a calls uno and has = 1 cards
        game.getNextPlayer();
        a.setUno();
        assertTrue(game.checkForUno());



    }
}
