package Source;

import java.util.ArrayList;

import Decks.DrawDeck;
import Decks.PlayDeck;

public class Game {
    public ArrayList<Player> players = new ArrayList<Player>();
    public boolean direction = true;
    public boolean isOver = false;
    public DrawDeck drawDeck = new DrawDeck();
    public PlayDeck playDeck = new PlayDeck();
    public Player currentPlayer = null;

    public Game(){}


    public Player getNextPlayer(){
        return null;
    }

    public boolean addPlayer(String name){return false;}

    public void run(){}

    /**
     *
     * @param card - card that the current player wants to play
     * @return - true if the move is valid and move was completed, false if the move is invalid
     */
    public boolean numberCard(Card card){return false;}

    private void wild(){}

    private void wildDrawFour(){}

    private void drawTwo(){}

    private void skip(){}

    private void reverse(){}

    private void reshuffle(){}

    private String readInput(){return null;}

    private boolean validPlayCheck(Card card){
        return false;
    }

    private void printScore(){}

    private ArrayList<Enum> convertInputToEnums(String input){return null;}

    private boolean play(Card card){return false;}

    private void checkWin(){}

    private void askForRematch(){}

    private void newDrawDeck(){}

}
