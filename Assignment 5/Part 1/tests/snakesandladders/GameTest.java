package snakesandladders;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    /**
     * Scenario: The Board gets created in the game and receive an input for the size(). It should'nt be smaller than
     * two.
     * Tests: We test some different inputs for the createBoard to see if it will create a Board or not.
     * Resolve: Found a bug. We added a upper bound since you could create huge Board that would crash the game.
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
     * Scenario: The game generates a new Board with the createBoard() method. After it's executed, there should be at
     * (boardSize/4) amount of snakes and ladders randomly generated if the board size is bigger than 5.
     * Tests: The way we generate snakes and ladders is random but a fixed amount. There is a chance that this test
     * will be marked false since it's possible that one (snake or ladder) will not find a corresponding partner where
     * he gets the ladder up or gets the snake down, so the ladder or the snake will not be generated(i.e. there will be one less
     * ladder or snake). But since this chance is very low and even lower the bigger the board gets, we didn't put this
     * in account for the test. It also checks if the boardSize is correct.
     */
    @Test
    public void createBoardTest2(){
        Game game1 = new Game();
        int snakeCount1 = 0;
        int ladderCount1 = 0;
        game1.createBoard(5,1);
        for (Square square:game1.squares){ //counts how many snakes
            if (square.getClass() == Snake.class){
                snakeCount1 += 1;}
        }
        for (Square square:game1.squares){//counts how many ladders
            if (square.getClass() == Ladder.class){
                ladderCount1 += 1;
            }
        }
        assertTrue(0== snakeCount1); //it's smaller than 6
        assertTrue(0== ladderCount1);
        assertTrue(game1.squares.size() == 5);

        Game game2 = new Game();
        game2.createBoard(60,60);// generates Board
        int snakeCount2 = 0;
        int ladderCount2 = 0;
        for (Square square:game2.squares){ //counts how many snakes
            if (square.getClass() == Snake.class){
            snakeCount2 += 1;}
        }
        for (Square square:game2.squares){//counts how many ladders
            if (square.getClass() == Ladder.class){
                ladderCount2 += 1;
            }
        }
        assertTrue((game2.board_size/4)/2 == snakeCount2);
        assertTrue((game2.board_size/4)/2 == ladderCount2);
        assertTrue(game2.squares.size() == 3600); //60*60

    }

    /**
     * Scenario: It starts and the game wants to know how large the board should be.
     * Tests: This method checks the users input. The input is correct when it's an integer and not negative.
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
     * Scenario: The player rolls the dice and gets a number that would lead him to reach beyond the last square.
     * Tests: In this case it would calculate a new number to move. For example we test a boardSize of 4.
     * If the user rolls 6 the player would reach the end and then logically come
     * back to the FirstSquare since he moves back 3 steps from the lastSquare. It should calculate 0.
     */
    @Test
    public void checkNumberTest(){
        Game game = new Game();
        game.createBoard(2,2);
        game.addPlayerToBoard("TestName");
        game.numPlayer = 1;
        assertTrue(game.checkNumber(6, game.players.get(0))== 0);

        Game game1 = new Game();
        game1.createBoard(1,2);
        game1.addPlayerToBoard("TestName");
        game1.numPlayer = 1;
        assertTrue(game1.checkNumber(6, game1.players.get(0))== 0);
    }

    /**
     * Scenario: One player reaches the last square
     * Tests: This method checks if a player has reached the last square which will lead to the end of the game. This should
     * change the winner to the player name.
     */
    @Test
    public void checkLast(){
        Game game = new Game();
        game.createBoard(1,6);
        game.addPlayerToBoard("TestName");
        game.numPlayer = 1;
        Player current_player = game.players.get(game.current);
        int to_move = game.checkNumber(5, current_player);
        current_player.move(to_move);
        game.checkLast(current_player);
        assertTrue(game.winner == current_player);
    }
}
