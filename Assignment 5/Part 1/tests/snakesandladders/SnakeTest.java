package snakesandladders;
import org.junit.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

public class SnakeTest {
        /**
         * This method test the enter() method from Snake.
         * First we generate a Board and simulate a run(). Then we search for a snake and move the player directly
         * to the snake to see if he appears in the corresponding square and not the snake.
         */
        @Test
        public void enter() {
                Game game = new Game();
                game.createBoard(5,5);
                game.addPlayerToBoard("TestName");
                //run() simulated
                Player current_player = game.players.get(game.current);
                game.numPlayer = 1;
                int SnakeSearch = -1;
                for(Square square:game.squares){//counts how many Snakes
                        SnakeSearch += 1;
                        if(square.getClass() == Snake.class){
                                break;
                        }
                }
                int to_move = game.checkNumber(SnakeSearch, current_player);
                current_player.move(SnakeSearch);
                assertTrue((game.squares.get(((Snake)game.squares.get(SnakeSearch)).new_position-1)).player_list.get(0).getName() == "TestName"); //test if player down up the snake
        }
}
