package snakesandladders;

import org.junit.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SquareTest {
    @Test
    public void enterTest(){
        Game game = new Game();
        game.createBoard(6,1);
        game.addPlayerToBoard("TestName");
        //run() simulated
        Player current_player = game.players.get(game.current);
        game.numPlayer = 1;
        int to_move = game.checkNumber(5, current_player); //to be sure that it isn't a snake or a ladder, LastSquare only has an extra boolean
        current_player.move(to_move);
        game.nextPlayer();
        assertTrue(game.squares.get(5).player_list.get(0).name == "TestName");
    }

    @Test
    public void leaveTest(){
        Game game = new Game();
        game.createBoard(6,1);
        game.addPlayerToBoard("TestName");
        //run() simulated
        game.numPlayer = 1;
        Player current_player = game.players.get(game.current);
        int to_move = game.checkNumber(5, current_player); //to be sure that it isn't a snake or a ladder, doesn't matter if it' s LastSquare only has an extra boolean
        current_player.move(to_move);
        game.squares.get(5).leave(current_player);
        assertTrue(game.squares.get(5).player_list.size() == 0);

    }

    @Test
    public void moveAndLandAndFindSquareTest(){//combined 2 tests
        Game game = new Game();
        game.createBoard(1,5);//Because it doesn't generate snakes or ladders
        game.addPlayerToBoard("TestName");
        Square nextSquare = game.squares.get(0).moveAndLand(4); //pretend we have to move 4 from the beginning as a Player
        assertTrue(nextSquare.position == 5);

    }
    @Test
    public void isOccupiedTest(){
        Game game = new Game();
        game.createBoard(1,6);//Because it doesn't generate snakes or ladders
        game.addPlayerToBoard("TestName");
        game.numPlayer = 1;
        Player current_player = game.players.get(game.current);
        int to_move = game.checkNumber(5, current_player); //to be sure that it isn't a snake or a ladder, doesn't matter if it' s LastSquare only has an extra boolean
        current_player.move(to_move);
        assertTrue(game.squares.get(5).isOccupied());

    }
}
