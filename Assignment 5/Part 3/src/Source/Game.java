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
    Player currentPlayer = new Player();


    Player getNextPlayer(){
        return null;
    }

    public void run(){}

    public void addPlayer(Player p){}

    /**
     *
     * @param card - card that the current player wants to play
     * @return - true if the move is valid and move was completed, false if the move is invalid
     */
    public boolean numberCard(Card card){
        return false;
    }

    public void wild(){}

    public void wildDrawFour(){}

    public void drawTwo(){}

    public void skip(){}

    public void reverse(){}

    public void reshuffle(){}

    public void readInput(){}

    public boolean validPlayCheck(String s){
        return false;
    }

    public void printScore(){}

    private void addCardToPlayDeck(Card card){}

    private void removeFromDrawDeck(Card card){}

}
