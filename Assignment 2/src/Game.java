import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Board gameboard;
    private Logic logic;
    private Player white;
    private Player black;
    private ArrayList<Player> current; //to know who is playing

    Game(){
        this.gameboard = new Board();
        this.logic = new Logic();
        this.white = new Player(initPlayer(Player.colors.WHITE), Player.colors.WHITE);
        this.black = new Player(initPlayer(Player.colors.BLACK), Player.colors.BLACK);
        this.current.add(this.white);
        this.current.add(this.black);
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

    /**
     * @param newX - x position where figure is to be moved
     * @param newY - y position where figure is to be moved
     * @return - true or false depending if the coordinates are in range
     */
    public boolean isLegalMove(int newX, int newY){
        if((0 <= newX && newX >= 7) && (0 <= newY && newY >= 7))
            return true;
        else
            return false;
    }

    /**
     *
     * @param gameboard - chess game board
     * @param newX - x position where figure is to be moved
     * @param newY - y position where figure is to be moved
     * @return - true or false depending if there is a figure in [newX,newY]: False = NOT occupied
     */
    public boolean isOccupied(Board gameboard, int newX, int newY){
        if (gameboard.board[newX][newY][1] == null)
            return false;
        else
            return true;
    }

}

