import java.util.Scanner;

public class PlayerS {
    String name;
    int pos;

    PlayerS(){
        //starting at block 0
        pos = 0;
    }

    public void setName(int i){
        Scanner s = new Scanner(System.in);
        System.out.println("Player " + i + ":");

        name = s.nextLine();

    }

    public String getName(){
        return this.name;
    }

}