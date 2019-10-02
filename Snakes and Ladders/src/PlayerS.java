import java.util.Scanner;

public class PlayerS {
    String name;
    int pos;

    PlayerS(){
        //starting at block 0
        pos = 0;
    }

    public void setName(){
        Scanner s = new Scanner(System.in);
        name = s.nextLine();

    }

    public String getName(){
        return this.name;
    }

}