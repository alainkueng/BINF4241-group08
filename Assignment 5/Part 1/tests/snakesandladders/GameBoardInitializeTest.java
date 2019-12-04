package snakesandladders;
import org.junit.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBoardInitializeTest {
    @Test
    public void createBoardTest() {
        Game game = new Game();
        assertTrue(game.createBoard(6, 6));
        assertTrue(game.createBoard(1, 2));
        assertTrue(game.createBoard(100, 100));
        assertFalse(game.createBoard(0, 0));
        assertFalse(game.createBoard(0, 2));
        assertFalse(game.createBoard(100, 101));
        assertFalse(game.createBoard(1, 1));
        assertFalse(game.createBoard(0, 0));
    }
    @Test
    public void creatBoardTest2(){
        Game game = new Game();
        game.createBoard(60,60);
        int snakeCount = 0;
        int ladderCount = 0;
        for (Square square:game.squares){
            if (square.getClass() == Snake.class){
            snakeCount += 1;}
        }
        for (Square square:game.squares){
            if (square.getClass() == Ladder.class){
                ladderCount += 1;
            }
        }
        assertTrue((game.board_size/4)/2 == snakeCount);
        assertTrue((game.board_size/4)/2 == ladderCount);
        assertTrue(game.board_size == 3600);

    }
    @Test
    public void checkNumberStringForCreateBoardTest(){//Only checks if the input is a natural positive number
        Game game = new Game();
        assertFalse(game.checkNumberStringForCreateBoard("-1"));
        assertFalse(game.checkNumberStringForCreateBoard("abc"));
        assertFalse(game.checkNumberStringForCreateBoard("+*ç%&&/()="));
        assertFalse(game.checkNumberStringForCreateBoard("0.5"));
        assertTrue(game.checkNumberStringForCreateBoard("0"));
        assertTrue(game.checkNumberStringForCreateBoard("100000"));
        assertTrue(game.checkNumberStringForCreateBoard("2"));

    }

}
