package snakesandladders;

import java.util.List;
import java.util.Scanner;

public class Player { // Attribute
    String name;
    Square square;


    public Player(Square number) { //the constructor
       this.square = number;
    }

    public void setName(String playerName) {
    this.name = playerName;
    }


    public String getName() {
        return this.name;
    }

    public void move(int to_move) {
        if(to_move != 0) {
            Square next_square = this.square.moveAndLand(to_move);
            this.square.leave(this);
            this.square = next_square;
            next_square.enter(this);
        }
    }
}
