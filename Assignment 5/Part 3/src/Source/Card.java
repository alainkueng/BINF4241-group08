package Source;
public class Card {

    public int score = -1;
    public CardColor col;
    public CardType ty;

    public Card(CardColor color, CardType type, int score){
        this.col = color;
        this.ty = type;
        this.score = score;
    }
}