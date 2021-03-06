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


    /**
     * @return - Gets the next player from the players list.
     */
    public Player getNextPlayer(){
        return null;
    }

    /**
     * @param name - Adds the player to the Game with the string name given.
     * @return - true if it worked
     */
    public boolean addPlayer(String name){return false;}

    /**
     * runs all the methods in the right order.
     */
    public void run(){}

    /**
     *
     * @param card - card that the current player wants to play.
     * @return - true if the move is valid and move was completed, false if the move is invalid.
     */

    public void numberCard(Card card){}

    /**
     * @param card - receives a card and puts it on the PlayDeck. Wild color should be changed to the color that is
     *               chosen by the player.
     */
    public void wild(Card card){}

    /**
     * @param card - receives a card and puts it on the PlayDeck. Forces the next player to get 4 cards and changes
     *               color of the card to the chosen one.
     */
    public void wildDrawFour(Card card){}

    /**
     * @param card - receives a card and puts it on the PlayDeck. Forces the next player to get 2 cards.
     */
    public void drawTwo(Card card){}

    /**
     * @param card - receives a card and puts it on the PlayDeck. Skips the next Player. (currentPlayer)
     */
    public void skip(Card card){}

    /**
     * @param card - receives a card and puts it on the PlayDeck. Changes the Direction.
     */
    public void reverse(Card card){}

    /**
     * Shuffles the playDeck minus one and converts it to the drawDeck.
     */
    public DrawDeck reshuffle(){return null;}

    /**
     * @return Just reads the commandLine input. Not to be tested. Java methods only.
     */
    public String readInput(){return null;}

    /**
     * @param number - Gets the readInput String and checks if it's valid.
     * @return False if the input doesn't convert into a number.
     */
    public boolean inputCheckForNumber(String number){return false;}

    /**
     * @param name - Gets the readInput String and checks if it's valid
     * @return False if the input doesn't convert into a valid name.
     */
    public boolean inputCheckForName(String name){return false;}

    /**
     * @param Command - Gets the readInput String and checks if it's a valid command
     * @return False if the input doesn't convert into a valid enum
     */
    public boolean inputCheckForCommand(String Command){return false;}

    /**
     * @param card - Gets the card from the player
     * @return False if the card can't be put on the playDeck.
     */
    public boolean validPlayCheck(Card card){
        return false;
    }

    /**
     * Prints the Score for all players
     */
    public void printScore(){}

    /**
     * @param input gets the input from readInput().
     * @return Converts the string in int and enums into an ArrayList<Object>. [enum color, int number, enum type, boolean uno, boolean claimUno, boolean claimPlusFour]
     */
    public ArrayList<Object> convertInputToCommand(String input){return null;}

    /**
     * @param card The player chooses a card wants to play it.
     * @return Rejects if it's not possible to play.
     */
    public boolean play(Card card){return false;}

    /**
     * Checks if someone has no more cards and puts the winner boolean to true.
     */
    public void checkWin(){}

    /**
     * Asks the player if they want to end the game after one game is over or rematch.
     */
    public void askForRematch(){}

    /**
     * 1. If someone announces UNO, check if they really only have one card.<br>
     *     2. If someone forgets to say UNO and the next player denounces this: Player who forgot to say uno most
     *     draw 2 cards.
     */
    public boolean checkForUno(){return false;}

    /**
     * @param uno - if true, checks if he can set uno for the player and does it if he can do so.
     * @param claimUno - if true, checks if the other said uno before ending, if false reverts the play.
     * @param claimPlusFour - if true, checks if the other could have laid any card of the same color before, if true reverts the play.
     */
    public void checkClaims(boolean uno, boolean claimUno, boolean claimPlusFour){}

}
