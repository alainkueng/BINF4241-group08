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
        @Test
        public void enter() {
                Game game = new Game();
                game.createBoard(5,5);
                game.addPlayerToBoard("TestName");
                //run() simulated
                Player current_player = game.players.get(game.current);
                game.numPlayer = 1;
                int LadderSearch = -1;
                for(Square square:game.squares){
                        LadderSearch += 1;
                        if(square.getClass() == Snake.class){
                                break;
                        }
                }
                int to_move = game.checkNumber(LadderSearch, current_player); //to be sure that it isn't a snake or a ladder, LastSquare only has an extra boolean
                current_player.move(LadderSearch);
                assertTrue((game.squares.get(((Snake)game.squares.get(LadderSearch)).new_position-1)).player_list.get(0).getName() == "TestName"); //test if player goes up the ladder
        }
}
