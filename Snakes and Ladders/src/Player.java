import java.util.List;
import java.util.Scanner;

public class Player { // Attribute
    String name;
    Square square;


    public Player(Square number) { //the constructor
       this.square = number;
    }

    public void setName(int number, List<Player> player_list) {
        Scanner s = new Scanner(System.in);
        System.out.println("Player " + number + ":");
        name = s.nextLine();
        if (player_list.size() > 0) {
            for (int i = 0; i < player_list.size(); i++) {
                // no repeated names
                while (name.equals(player_list.get(i).name)) {
                    System.out.println("This name has already been used, please try another one:");
                    name = s.nextLine();
                    i = 0;
                }
            }
        }
        while(!name.matches("[a-zA-Z ]+" )) {
            System.out.println("Please enter a valid name!");
            name = s.nextLine();
        }
        this.name = name;
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
