package Source;

import java.util.ArrayList;

public class Player {
    ArrayList<Card> handCards = new ArrayList<Card>();
    String name = "";
    int score = -1;
    boolean saidUno = false;

    /**
     * @param name - Sets the name for the player .
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * @return Gets the name from the player.
     */
    public String getName(){return null;}

    /**
     * @return Calculates the handCards points and returns it.
     */
    public int getScore(){return 0;}

    /**
     * prints the handCards.
     */
    public void printCards(){}

    /**
     * @param card Add the card to the handCards.
     */
    public void addCard(Card card){}

    /**
     * @param color
     * @param type
     * @param number
     * Searches for the Card in the hand with this configuration.
     */
    public Card getCard(CardColor color, CardType type, int number){return null;}

    /**
     * @param card removes the received Card from his hands.
     */
    public void removeCard(Card card){}

    /**
     * Removes all handCards to reset the game and puts it on the playDeck.
     */
    public void removeAllCard(){}

    /**
     * sets uno to true when hes say's he has uno.
     */
    public void setUno(){}
}
