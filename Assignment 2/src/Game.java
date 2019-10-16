import java.util.Scanner;

public class Game {
    private Board gameboard;
    private Logic logic;
    private Player white;
    private Player black;

    Game(){
        this.gameboard = new Board();
        this.logic = new Logic();
        this.white = new Player(initPlayer(Player.colors.WHITE), Player.colors.WHITE);
        this.black = new Player(initPlayer(Player.colors.BLACK), Player.colors.BLACK);
    }
    private String initPlayer(Player.colors color){
        Scanner player = new Scanner(System.in);
        boolean stringCheck = false;
        String name = null;
        while (!stringCheck){
            System.out.printf("Please enter playername for the %s side.\n", color);
            name = player.nextLine();
            if (name.matches("^[a-zA-Züöäéàèçëòêâôîï]*$")&& !name.equals("") && name.length() < 15){
                stringCheck = true;
                if(color == Player.colors.BLACK && white.getName().equals(name)){
                    stringCheck = false;
                    System.out.println("Name already used!");
                }
            }
            else{System.out.println("Please input a name with max. length 15 letters on a Swiss Keyboard");
            }
        }
        return name;
    }
}

