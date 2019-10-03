

public class Player { // Attribute
    String name;
    int pos;


    public Player() { //the constructor
        this.pos = 0;  //position on board, starting at 0
    }

    public void setName(int i) {
        Scanner s = new Scanner(System.in);
        System.out.println("Player " + i + ":");
        name = s.nextLine();
    }

    public String getName() {
        return this.name;
    }
}


