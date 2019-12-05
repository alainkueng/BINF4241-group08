package snakesandladders;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LadderTest {
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
            if(square.getClass() == Ladder.class){
                break;
            }
        }
        int to_move = game.checkNumber(LadderSearch, current_player); //to be sure that it isn't a snake or a ladder, LastSquare only has an extra boolean
        current_player.move(LadderSearch);
        assertTrue((game.squares.get(((Ladder)game.squares.get(LadderSearch)).new_position-1)).player_list.get(0).getName() == "TestName"); //test if player is down the ladder
    }
}
