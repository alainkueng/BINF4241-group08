import java.util.Scanner;

public class Player { // Attribute
    String name;
    Square square;


    public Player(Square number) { //the constructor
       this.square = number;
    }

    public void setName(int number) {
        String[] past = new String[4];
        Scanner s = new Scanner(System.in);
        System.out.println("Player " + number + ":");
        name = s.nextLine();
        past[number-1] = name;
        for (int i = 0; i <= number-1; i++) {
            // no repeated names
            if (i != 0 && past[i].equals(name)) {
                while (past[i].equals(name)) {
                    System.out.println("This name is already in use, please use another one: ");
                    name = s.nextLine().toLowerCase();
                }
            }
        }
        while(!name.matches("[a-zA-Z ]+" )) {
            System.out.println("Please enter a valid name!");
            name = s.nextLine();
        }

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
