import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Board gameboard;
    private Player white;
    private Player black;
    private ArrayList<Player> current = new ArrayList<>(); //to know who is playing

    Game(){
        this.gameboard = new Board();
        this.white = new Player(initPlayer(Player.colors.WHITE), Player.colors.WHITE); //Create white Player
        this.black = new Player(initPlayer(Player.colors.BLACK), Player.colors.BLACK); //Create black Player
        this.current.add(this.white); //Add white Player to current list to track who's turn it is
        this.current.add(this.black); //Add black Player to current list to track who's turn it is
    }
    /**
     * @param color - to ask for player
     * @return - String Name to create a player() after
     */
    private String initPlayer(Player.colors color){
        Scanner player = new Scanner(System.in);
        boolean stringCheck = false; //boolean to check if input is correct
        String name = null;
        while (!stringCheck){
            System.out.printf("Please enter playername for the %s side.\n", color);
            name = player.nextLine();
            if (name.matches("^[a-zA-Züöäéàèçëòêâôîï]*$")&& !name.equals("") && name.length() < 15){ //checks for alphabet, for no input and for input is longer than 15
                stringCheck = true;
                if(color == Player.colors.BLACK && white.getName().equals(name)){// checks if name is already used
                    stringCheck = false;
                    System.out.println("Name already used!");
                }
            }
            else{System.out.println("Please input a name with max. length 15 letters on a Swiss Keyboard");//prints if stringCheck is wrong, to let user know that input is wrong
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
    private Object[] parseInput(String input){
        /**
         * @return: returns an array [(class) Figure Type, (int) row-coordinate current, (int) column-coordinate current, (int) row-c move, (int) col-c move,]
         */

        int length = input.length();
        Object[] parsedInput = new Object[5];

        ArrayList<Character> mapping = new ArrayList<Character>();
        mapping.add('a');
        mapping.add('b');
        mapping.add('c');
        mapping.add('d');
        mapping.add('e');
        mapping.add('f');
        mapping.add('g');
        mapping.add('h');


        Map <Character, Class> figureCatalog = new HashMap<Character, Class>();
        figureCatalog.put('R', Rook.class);
        figureCatalog.put('B', Bishop.class);
        figureCatalog.put('N', Knight.class);
        figureCatalog.put('Q', Queen.class);
        figureCatalog.put('K', King.class);

        if (length == 2){
            parsedInput[0] = Pawn.class;
            parsedInput[3] = Character.digit(input.charAt(1),10);
            parsedInput[4] = mapping.indexOf(input.charAt(1));
        }
        else if(length == 3){
            parsedInput[0] = figureCatalog.get(input.charAt(0));
            parsedInput[3] = Character.digit(input.charAt(2),10);
            parsedInput[4] = mapping.indexOf(input.charAt(1));
        }
        else if(length == 4){
            parsedInput[0] = figureCatalog.get(input.charAt(0));
            parsedInput[2] = mapping.indexOf(input.charAt(1));
            parsedInput[3] = Character.digit(input.charAt(3),10);
            parsedInput[4] = mapping.indexOf(input.charAt(2));
        }
        else if(length == 5){
            parsedInput[0] = figureCatalog.get(input.charAt(0));
            parsedInput[1] = Character.digit(input.charAt(2), 10);
            parsedInput[2] = mapping.indexOf(input.charAt(1));
            parsedInput[3] = Character.digit(input.charAt(4),10);
            parsedInput[4] = mapping.indexOf(input.charAt(3));
        }
        return parsedInput;
    }
}

