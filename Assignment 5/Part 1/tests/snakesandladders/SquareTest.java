package snakesandladders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class SquareTest {
    /**
     * This test checks if the move from the player lands on the right square. We chose the lastSquare since
     * it's for sure not a ladder or a snake. It only has an extra variable which differs it from the normal square.
     * We first simulate run() and then we check in the assert if it worked. Snakes and Ladders will be tested in
     * the LadderTest file and the SnakeTest file.
     */
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

    /**
     * Checks if the square get left correctly if the method get called. The Player should leave the squareList.
     */
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
        game.squares.get(5).leave(current_player); // the Player leaves the Square
        assertTrue(game.squares.get(5).player_list.size() == 0);

    }

    /**
     * Since one method calls the other in the return, we tests the two methods here in one test method. The method gets
     * the number after its checked. It will find and set the next square.
     */
    @Test
    public void moveAndLandAndFindSquareTest(){//combined 2 tests
        Game game = new Game();
        game.createBoard(1,5);
        game.addPlayerToBoard("TestName");
        Square nextSquare = game.squares.get(0).moveAndLand(4); //pretend we have to move 4 from the beginning as a Player
        assertTrue(nextSquare.position == 5);

    }

    /**
     * Scenario: A player moves to a square.
     * Tests: This test return if the square we want to move our player to is occupied or not. It returns true if there is
     * a player on the square and will return false if the square is free.
     */
    @Test
    public void isOccupiedTest(){
        Game game = new Game();
        game.createBoard(1,6);
        game.addPlayerToBoard("TestName");
        game.numPlayer = 1;
        Player current_player = game.players.get(game.current);
        assertFalse(game.squares.get(5).isOccupied()); //free
        int to_move = game.checkNumber(5, current_player); //to be sure that it isn't a snake or a ladder, doesn't matter if it' s LastSquare only has an extra boolean
        current_player.move(to_move);
        assertTrue(game.squares.get(5).isOccupied());

    }
}
