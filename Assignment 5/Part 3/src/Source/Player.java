package Source;

import java.util.ArrayList;

public class Player {
    ArrayList<Card> handCards = new ArrayList<Card>();
    String name = "";
    int score = -1;

    public Player(String name){
        this.name = name;
    }

    public String getName(){return null;}

    public int getScore(){return 0;}

    public void printCards(){}

    public void addCard(Card card){}

    public void getCard(CardColor color, CardType type, int number){}

    public void removeCard(Card card){}

    public void removeAllCard(){}
}
