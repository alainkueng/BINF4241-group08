package snakesandladders;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class InputPlayersToGameTest {

    /**
     * Scenario: You enter a name for a player. It's expected that the method puts it on the first square.
     * Tests: This test checks the addPlayerToBoard method from Game(). This method gets an already checked String as input and
     * creates a player with the input as name. It also adds to the generated Player the corresponding Square. Both gets
     * tested.
     */
    @Test
    public void addPlayerIsInListTest(){
        Game game = new Game();
        game.createBoard(6,6);
        game.addPlayerToBoard("TestName1");
        game.addPlayerToBoard("TestName2");
        game.addPlayerToBoard("TestName3");
        game.addPlayerToBoard("TestName4");
        assertTrue(((Square)(game.squares.get(0))).player_list.get(0).getName() == "TestName1");
        assertTrue(((Square)(game.squares.get(0))).player_list.get(1).getName() == "TestName2");
        assertTrue(((Square)(game.squares.get(0))).player_list.get(2).getName() == "TestName3");
        assertTrue(((Square)(game.squares.get(0))).player_list.get(3).getName() == "TestName4");
        assertTrue(((game.players.get(0))).square.getClass()  == FirstSquare.class);
        assertTrue(((game.players.get(1))).square.getClass()  == FirstSquare.class);
        assertTrue(((game.players.get(2))).square.getClass()  == FirstSquare.class);
        assertTrue(((game.players.get(3))).square.getClass()  == FirstSquare.class);
    }

    /**
     * Scenario: The user inputs the amount of player he wants to play with.
     * Tests: This tests the checkNumberStringForAddPlayer method from Game(). It checks how many players the user wants
     * to add to the game. If the String input is lower than 1 or higher than 4 it will return false. It adds the number
     * to the numPlayer global variable which we test below and it should remain the same if the String is false.
     * It return false if it couldn't add the numPlayer.
     *
     */
    @Test
    public void checkNumberStringForAddPlayerTest(){
        Game game = new Game();
        game.checkNumberStringForAddPlayer("1");
        assertTrue(game.numPlayer == 1);
        game.checkNumberStringForAddPlayer("2");
        assertTrue(game.numPlayer == 2);
        game.checkNumberStringForAddPlayer("3");
        assertTrue(game.numPlayer == 3);
        game.checkNumberStringForAddPlayer("4");
        assertTrue(game.numPlayer == 4);
        game.checkNumberStringForAddPlayer("5");
        assertTrue(game.numPlayer == 4); //since it should remain 4
        game.checkNumberStringForAddPlayer("0");
        assertTrue(game.numPlayer == 4);//since it should remain 4

    }

    /**
     * Scenario: The user inputs the same name for two players.
     * Tests: This test checks the compareNames method from Game(). We input a String name and it will return a boolean
     * if the player name is already used before.
     */
    @Test
    public void compareNamesTest(){
        Game game = new Game();
        game.createBoard(6,6);
        assertTrue(game.compareNames("TestName1"));//checks when none is added and compared
        game.addPlayerToBoard("TestName1");
        assertTrue(game.compareNames("TestName2"));
        game.addPlayerToBoard("TestName2");
        assertFalse(game.compareNames("TestName1"));
        assertFalse(game.compareNames("TestName2"));
    }

    /**
     * Scenario: The user inputs a name for a player
     * Tests: This test checks the checkPlayerStringTest method from Game() . It returns false when the
     * input string contains not-alphabetic words or is just an empty name. Here we found a bug where you could
     * name yourself the same as the person before and you could input a empty string. All this is tested below.
     */
    @Test
    public void checkPlayerStringTest(){
        Game game = new Game();
        game.createBoard(6,6);
        assertTrue(game.checkPlayerString("TestName"));
        assertFalse(game.checkPlayerString("Test1234"));
        assertFalse(game.checkPlayerString("test+*ç%&/()="));
        assertTrue(game.checkPlayerString("Test Name"));
        assertFalse(game.checkPlayerString(""));//length 0
        assertFalse(game.checkPlayerString(" "));
        assertFalse(game.checkPlayerString("          "));
        assertTrue(game.checkPlayerString("T"));//length 1
        assertFalse(game.checkPlayerString("abcdefghijklmnop"));//length 16
        assertTrue(game.checkPlayerString( "abcdefghijklmno"));//length 15
    }
}










