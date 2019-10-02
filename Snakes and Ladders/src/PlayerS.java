import java.util.Scanner;

public class PlayerS {
    String name;

    PlayerS(){
    }

    public void setName(){
        Scanner s = new Scanner(System.in);
        name = s.nextLine();

    }

    public String getName(){
        return this.name;
    }

}