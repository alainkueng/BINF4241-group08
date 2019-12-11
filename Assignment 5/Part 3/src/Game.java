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


    private Player getNextPlayer(){
        return null;
    }

    public void run(){}

    public void numberCard(){}

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

}
