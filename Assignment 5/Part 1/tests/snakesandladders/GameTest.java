package snakesandladders;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    /**
     * This method tests if the Board can be generated. It should return false if the Board this smaller than 2
     * and greater than 10000. We added a limit to the creatBoard(), because we found a bug where you could
     * create a huge Board which will crash the Game.
     */
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

    /**
     * This method tests if the Board generates the right amount of snakes and ladders. The way we generate snakes and
     * ladders is random but a fixed amount (BoardSize/4). There is a chance that this method will be false since
     * it's possible in the implementation that one will not find a corresponding partner where he gets the
     * ladder up or gets the snake down, so the ladder or the snake will not be generated(i.e. there will be one less
     * ladder or snake). But since this chance is very low and even lower the bigger the board gets, we didn't put this
     * in account for the test. It also checks if the boardSize is correct.
     */
    @Test
    public void creatBoardTest2(){
        Game game = new Game();
        game.createBoard(60,60);// generates Board
        int snakeCount = 0;
        int ladderCount = 0;
        for (Square square:game.squares){ //counts how many snakes
            if (square.getClass() == Snake.class){
            snakeCount += 1;}
        }
        for (Square square:game.squares){//counts how many ladders
            if (square.getClass() == Ladder.class){
                ladderCount += 1;
            }
        }
        assertTrue((game.board_size/4)/2 == snakeCount);
        assertTrue((game.board_size/4)/2 == ladderCount);
        assertTrue(game.squares.size() == 3600); //60*60

    }

    /**
     * This method checks the users input. The input is correct when it's an integer and not negative.
     * It doesn't matter how high the number is.
     */
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

    /**
     * This method checks if the move goes over the boardSize. In this case it would calculate a new number to move.
     * For example we test a boardSize of 4. If the user rolls 6 the player would reach the end and then logically come
     * back to the FirstSquare.
     */
    @Test
    public void checkNumberTest(){
        Game game = new Game();
        game.createBoard(2,2);
        game.addPlayerToBoard("TestName");
        game.numPlayer = 1;
        assertTrue(game.checkNumber(6, game.players.get(0))== 0);
    }
}
