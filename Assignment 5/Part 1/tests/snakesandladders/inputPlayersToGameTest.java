package snakesandladders;
import org.junit.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class inputPlayersToGameTest {

    // We won't test readInput() since it only contains java integrated functions
    @Test
    public void addPlayerIsInListTest(){// we don't have to know how many players or if the string is correct. this is tested in other methods
        Game game = new Game();
        game.createBoard(6,6);
        game.addPlayerToBoard("TestName1");
        game.addPlayerToBoard("TestName2");
        game.addPlayerToBoard("TestName3");
        game.addPlayerToBoard("TestName4");
        assertTrue(((Square)(game.squares.get(0))).player_list.get(0).getName() == "TestName1","There is something wrong with the addPlayerToBoard function");
        assertTrue(((Square)(game.squares.get(0))).player_list.get(1).getName() == "TestName2","There is something wrong with the addPlayerToBoard function");
        assertTrue(((Square)(game.squares.get(0))).player_list.get(2).getName() == "TestName3","There is something wrong with the addPlayerToBoard function");
        assertTrue(((Square)(game.squares.get(0))).player_list.get(3).getName() == "TestName4","There is something wrong with the addPlayerToBoard function");
    }
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
    @Test
    public void compareNamesTest(){//this method should only test if the names in the list already exist
        Game game = new Game();
        game.createBoard(6,6);
        assertTrue(game.compareNames("TestName1"));//checks when none is added and compared
        game.addPlayerToBoard("TestName1");
        assertTrue(game.compareNames("TestName2"));
        game.addPlayerToBoard("TestName2");
        assertFalse(game.compareNames("TestName1"));
        assertFalse(game.compareNames("TestName2"));
    }

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










