import Decks.Deck;

public class Card extends Deck {
    public enum color {YELLOW,GREEN,RED,BLACK,BLUE};
    public enum type {REVERSE, WILD, SKIP, WILD_D4, DRAW_2, NORMAL};
    public int score = -1;
    public color col;
    public type ty;

    public Card(Card.color color, Card.type type, int score){
        this.col = color;
        this.ty = type;
        this.score = score;
    }
}