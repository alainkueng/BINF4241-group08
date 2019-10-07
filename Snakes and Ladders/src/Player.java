import java.util.Scanner;

public class Player { // Attribute
    String name;
    Square square;


    public Player(Square number) { //the constructor
       this.square = number;
    }

    public void setName(int number) {
        Scanner s = new Scanner(System.in);
        System.out.println("Player " + number + ":");
        name = s.nextLine();
        while(!name.matches("[a-zA-Z ]+" )){
            System.out.println("Please enter a valid name!");
            name = s.nextLine();
        }
    }

    public String getName() {
        return this.name;
    }

    public void move(int to_move) {
        Square next_square = this.square.moveAndLand(to_move);
        this.square.leave(this);
        this.square = next_square;
        next_square.enter(this);
    }
}
