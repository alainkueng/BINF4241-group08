package snakesandladders;
import org.junit.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBoardInitializeTest {
    @Test
    public void createBoardTest(){
        Game game = new Game();
        game.createBoard(6, 6);
    }
    public void checkNumberStringForCreateBoardTest(){
        Game game = new Game();
    }

}
