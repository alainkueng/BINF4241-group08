import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private Player white;
    private Player black;
    private ArrayList<Player> current = new ArrayList<Player>(); //to know who is playing

    Game(){
        this.gameBoard = new Board();
        this.white = new Player(initPlayer(Player.colors.WHITE), Player.colors.WHITE); //Create white Player
        this.black = new Player(initPlayer(Player.colors.BLACK), Player.colors.BLACK); //Create black Player
        this.current.add(this.white); //Add white Player to current list to track who's turn it is
        this.current.add(this.black); //Add black Player to current list to track who's turn it is
        parseInput(inputMoveAndCheck(white.getName())); // This needs to be changed for the loop/ gets input move and checks it and gives back an array [(class) Figure Type, (int) row-coordinate current, (int) column-coordinate current, (int) row-c move, (int) col-c move,]
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
            System.out.printf("Please enter player name for the %s side.\n", color);
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
     * @param gameBoard - chess game board
     * @param newX - x position where figure is to be moved
     * @param newY - y position where figure is to be moved
     * @return - true or false depending if there is a figure in [newX,newY]: False = NOT occupied
     */
    public boolean isOccupied(Board gameBoard, int newX, int newY){
        if (gameBoard.board[newX][newY][1] == null)
            return false;
        else
            return true;
    }
    private ArrayList inputMoveAndCheck(String currentPlayer){
        /**
         * @param currentPlayer - for asking whos turn it is
         * @return: returns the move input from the player after getting checked
         */
        ArrayList<Object> MoveKill = new ArrayList<Object>(); // List that should contain the input and if it kills the target
        Scanner player = new Scanner(System.in);
        boolean stringCheck = false; //boolean to check if input is correct
        String input = null;
        boolean capture = false; //to know if player wants to capture something
        while (!stringCheck){
            System.out.printf("Player %s enter your move:", currentPlayer);
            input = player.nextLine();
            int inputLength = input.length();

            if (inputLength == 2){//to move Pawns to a new field "e5")
                String char1 = Character.toString(input.charAt(0));
                String char2 = Character.toString(input.charAt(1));

                if (char1.matches("^[a-h]*$") & char2.matches("^[1-8]*$")){ //check for pawn move
                    stringCheck = true;
                }
            }
            if (inputLength == 3){  //to move a figure "Be5"
                                    //casteling on king side "o-o"
                                    //asking for a draw "(=)"
                String char1 = Character.toString(input.charAt(0));
                String char2 = Character.toString(input.charAt(1));
                String char3 = Character.toString(input.charAt(2));

                if (char1.matches("^[RBNQK]*$") & char2.matches("^[a-h]*$") & char3.matches("^[1-8]*$")){//normal move figure
                    stringCheck = true;
                }
                if (char1.matches("^[o]*$") & char2.matches("^[-]*$") & char3.matches("^[o]*$") & char3.matches("^[-]*$")){//casteling check
                    stringCheck = true;
                }
                if (char1.matches("^[(]*$") & char2.matches("^[=]*$") & char3.matches("^[)]*$")){//ask for drawcheck
                    stringCheck = true;
                }

            }
            if (inputLength == 4) {     //to kill with a figure if there is only one possibility "Bxd5"
                                        //kill with a pawn since you always give a line which indicates which pawn to take "exd5".
                                        //To move a figure if there are 2 possibilities "Bdb8"
                                        //pawn promotion indicated  "e8=Q"
                String char1 = Character.toString(input.charAt(0));
                String char2 = Character.toString(input.charAt(1));
                String char3 = Character.toString(input.charAt(2));
                String char4 = Character.toString(input.charAt(3));

                if (char1.matches("^[RBNQK]*$") & char2.matches("^[x]*$") & char3.matches("^[a-h]*$") & char4.matches("^[1-8]*$")) {//to kill with a figure check()
                    stringCheck = true;
                    capture = true;
                    input = char1 + char3 + char4; //input with no x

                }
                if (char1.matches("^[a-h]*$") & char2.matches("^[x]*$") & char3.matches("^[a-h]*$") & char4.matches("^[1-8]*$")) {//pawn kills someone check()
                    stringCheck = true;
                    capture = true;
                    input = char1 + char3 + char4; //input with no x

                }
                if (char1.matches("^[RBNQK]*$") & char2.matches("^[a-h]*$") & char3.matches("^[a-h]*$") & char4.matches("^[1-8]*$")) {//move figure if there are 2 possibilities
                    stringCheck = true;
                }
                if (char1.matches("^[a-h]*$") & char2.matches("^[1-8]*$") & char3.matches("^[=]*$") & char4.matches("^[RBNQK]*$")) {//check for promotion input
                    stringCheck = true;
                }
            }

            if (inputLength == 5) { //to kill if 2 figures could kill the same enemy figure "Bdxb8"
                                    //Casteling on Queenside "o-o-o"
                                    //to move with a figure, both x and y since they could reach the same place e.g Queen "Qh4e1"
                String char1 = Character.toString(input.charAt(0));
                String char2 = Character.toString(input.charAt(1));
                String char3 = Character.toString(input.charAt(2));
                String char4 = Character.toString(input.charAt(3));
                String char5 = Character.toString(input.charAt(4));

                if (char1.matches("^[RBNQK]*$") & char2.matches("^[a-h]*$") & char3.matches("^[x]*$") & char4.matches("^[a-h]*$") & char5.matches("^[1-8]*$")) {//check for 2 figures input
                    stringCheck = true;
                    capture = true;
                    input = char1 + char2 + char4 + char5;//change input

                }
                if (char1.matches("^[o]*$") & char2.matches("^[-]*$") & char3.matches("^[o]*$") & char4.matches("^[-]*$")& char5.matches("^[o]*$")) {//casteling queenside input
                    stringCheck = true;
                }
                if (char1.matches("^[RBNQK]*$") & char2.matches("^[a-h]*$") & char3.matches("^[a-h]*$") & char4.matches("^[1-8]*$")& char5.matches("^[1-8]*$")) {//move when 2 figures are on same x or y line input
                    stringCheck = true;
                }

            }


            if (inputLength == 6) { //to kill with a figure, both x and y since they are both are on the same line e.g Queen "Qh4xe1"
                String char1 = Character.toString(input.charAt(0));
                String char2 = Character.toString(input.charAt(1));
                String char3 = Character.toString(input.charAt(2));
                String char4 = Character.toString(input.charAt(3));
                String char5 = Character.toString(input.charAt(4));
                String char6 = Character.toString(input.charAt(5));

                if (char1.matches("^[RBNQK]*$") & char2.matches("^[a-h]*$") & char3.matches("^[1-8]*$") & char4.matches("^[x]*$") & char5.matches("^[a-h]*$") & char6.matches("^[1-8]*$")) {//check for 2 figures input
                    stringCheck = true;
                    capture = true;
                    input = char1 + char2 + char3 + char5 + char6;//change input
                }

            }

            if (inputLength == 8){ //make a en passant move "exd6e.p."
                String char1 = Character.toString(input.charAt(0));
                String char2 = Character.toString(input.charAt(1));
                String char3 = Character.toString(input.charAt(2));
                String char4 = Character.toString(input.charAt(3));
                String char5 = Character.toString(input.charAt(4));
                String char6 = Character.toString(input.charAt(5));
                String char7 = Character.toString(input.charAt(5));
                String char8 = Character.toString(input.charAt(5));

                if (char1.matches("^[a-h]*$") & char2.matches("^[x]*$") & char3.matches("^[a-h]*$") & char4.matches("^[1-8]*$") & char5.matches("^[a-h]*$") & char6.matches("^[.]*$") & char7.matches("^[p]*$") & char8.matches("^[.]*$")) {//check for 2 figures input
                    stringCheck = true;
                    capture = true;
                    //change input that raffi can make an enpassant
                }
            }

            else{System.out.println("Invalid Input");//prints if stringCheck is wrong, to let user know that input is wrong
            }
        }
        MoveKill.add(input);
        MoveKill.add(capture);

        return MoveKill;
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

