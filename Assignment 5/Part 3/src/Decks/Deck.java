package Decks;
import java.util.Stack;
import Source.*;


public abstract class Deck {

    public Stack<Card> cards = new Stack<Card>();

    /**
     * @return Pops the Card from the stack.
     */
    public Card pop(){return cards.pop();}

    /**
     * @param card Pushes the card into the stack.
     */
    public void push(Card card){}
}
