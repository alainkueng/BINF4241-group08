package snakesandladders;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LadderTest {
    /**
     * Scenario: A player moves to a ladder.
     * Tests: This tests if the ladder moves the player down the ladder to the corresponding square. First we generate a Board
     * and simulate a run(). Then we search for a ladder and move the player directly to the ladder to see if he appears
     * in the corresponding square and not the ladder.
     */
    @Test
    public void enter() {
        Game game = new Game();
        game.createBoard(5,5);
        game.addPlayerToBoard("TestName");
        //run() simulated
        Player current_player = game.players.get(game.current);
        game.numPlayer = 1;
        int LadderSearch = -1;
        for(Square square:game.squares){//searches for the first ladder
            LadderSearch += 1;
            if(square.getClass() == Ladder.class){
                break;
            }
        }
        int to_move = game.checkNumber(LadderSearch, current_player);
        current_player.move(LadderSearch);//moves him to the ladder
        assertTrue((game.squares.get(((Ladder)game.squares.get(LadderSearch)).new_position-1)).player_list.get(0).getName() == "TestName"); //tests if player is down the ladder
    }
}
