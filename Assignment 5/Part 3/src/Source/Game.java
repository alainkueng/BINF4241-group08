package Source;

import java.util.ArrayList;

import Decks.DrawDeck;
import Decks.PlayDeck;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();
    boolean direction = true;
    boolean isOver = false;
    DrawDeck drawDeck = new DrawDeck();
    PlayDeck playDeck = new PlayDeck();
    Player currentPlayer = null;


    public Player getNextPlayer(){
        return null;
    }

    private boolean addPlayer(String name){return false;}

    public void run(){}

    private void numberCard(){}
    public void addPlayer(Player p){}

    /**
     *
     * @param card - card that the current player wants to play
     * @return - true if the move is valid and move was completed, false if the move is invalid
     */
    public boolean numberCard(Card card){
        return false;
    }

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
