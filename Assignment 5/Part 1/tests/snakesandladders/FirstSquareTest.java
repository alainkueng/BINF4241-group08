package snakesandladders;
import org.junit.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FirstSquareTest {
    @Test
    public void leaveTest(){
        Game game = new Game();
        game.createBoard(6,1);
        game.addPlayerToBoard("TestName");
        game.addPlayerToBoard("TestName1");
        //run() simulated
        game.numPlayer = 2;
        Player current_player = game.players.get(game.current);
        int to_move = game.checkNumber(5, current_player); //to be sure that it isn't a snake or a ladder
        current_player.move(to_move);
        game.squares.get(0).leave(current_player);
        assertTrue(game.squares.get(0).player_list.size() == 1);
        game.nextPlayer();
        Player current_player2 = game.players.get(game.current);
        game.squares.get(0).leave(current_player2);
        assertTrue(game.squares.get(0).player_list.size() == 0);
    }

    @Test
    public void isOccupied() {
        Game game = new Game();
        game.createBoard(6,1);
        game.addPlayerToBoard("TestName");
        game.addPlayerToBoard("TestName1");
        assertFalse(game.squares.get(0).isOccupied()); //Should always be false
    }
}
