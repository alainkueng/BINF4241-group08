import java.util.Scanner;

public class Player { // Attribute
    String name;
    Square square;


    public Player(square) { //the constructor
       this.square = square;
    }

    public void setName(int i) {
        Scanner s = new Scanner(System.in);
        System.out.println("Player " + i + ":");
        name = s.nextLine();
    }

    public String getName() {
        return this.name;
    }
    public void move(int i) {



    }
}


// moveforward muss position ändern von int her, muss square sagen das sich attribute ändern
// move and land methode in moveforward