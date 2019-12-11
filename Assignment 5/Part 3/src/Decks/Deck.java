package Decks;
import java.util.Stack;
import Source.*;


public abstract class Deck {

    public Stack<Card> cards = new Stack<Card>();

    public Card pop(){return null;}

    public void push(Card card){}
}
