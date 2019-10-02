import java.util.Scanner;

public class PlayerS {
    String name;

    PlayerS(){
    }

    public void setName(){
        Scanner s = new Scanner(System.in);
        if(s.nextLine().equals(" ")){

        }
        name = s.toString();
    }
}