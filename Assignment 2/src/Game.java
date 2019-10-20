import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private Player white;
    private Player black;
    private Player currentPlayer;
    private boolean winner = false;

    Game(){
        this.gameBoard = new Board();
        this.white = new Player(initPlayer(Player.colors.WHITE), Player.colors.WHITE); //Create white Player
        this.black = new Player(initPlayer(Player.colors.BLACK), Player.colors.BLACK); //Create black Player
        this.currentPlayer = this.white; //Add white Player to current list to track whose turn it is
        System.out.println("Start state:");
        printBoard(gameBoard.getBoard());
        while(!winner){
            parseInput(inputMoveAndCheck(currentPlayer.getName())); // This needs to be changed for the loop/ gets input move and checks it and gives back an array [(class) Figure Type, (int) row-coordinate current, (int) column-coordinate current, (int) row-c move, (int) col-c move,]
            printBoard(gameBoard.getBoard());
            this.currentPlayer = playersTurn(currentPlayer);
        }
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
     *
     * @param gameBoard - chess game board
     * @param newX - x position where figure is to be moved
     * @param newY - y position where figure is to be moved
     * @return - true or false depending if there is a figure in [newX,newY]: False = NOT occupied
     */
    public boolean isOccupied(Board gameBoard, int newX, int newY){
        return gameBoard.board[newX][newY][1] != null;
    }

    /**
     * @param currentPlayer - for asking whose turn it is
     * @return returns the move input from the player after getting checked
     */
    private Object[] inputMoveAndCheck(String currentPlayer){
        String[] moveKill;
        Object[] checkedMove = new Object[2];
        Scanner player = new Scanner(System.in);
        boolean stringCheck = false; //boolean to check if input is correct
        String input = null;
        boolean capture = false; //to know if player wants to capture something
        while (!stringCheck){
            System.out.printf("Player %s enter your move: ", currentPlayer);
            input = player.nextLine();
            moveKill = input.split("");
            int inputLength = input.length();
            if (inputLength == 2){//to move Pawns to a new field "e5")
                if (moveKill[0].matches("^[a-h]*$") & moveKill[1].matches("^[1-8]*$")){ //check for pawn move
                    stringCheck = true;
                }
            }
            else if (inputLength == 3){  //to move a figure "Be5"
                                    //castling on king side "o-o"
                                    //asking for a draw "(=)"
                if (moveKill[0].matches("^[RBNQK]*$") & moveKill[1].matches("^[a-h]*$") & moveKill[2].matches("^[1-8]*$")){//normal move figure
                    stringCheck = true;
                }
                if (moveKill[0].matches("^[o]*$") & moveKill[1].matches("^[-]*$") & moveKill[2].matches("^[o]*$") & moveKill[2].matches("^[-]*$")){//casteling check
                    stringCheck = true;
                }
                if (moveKill[0].matches("^[(]*$") & moveKill[1].matches("^[=]*$") & moveKill[2].matches("^[)]*$")){//ask for drawcheck
                    stringCheck = true;
                }
            }
            else if (inputLength == 4) {     //to kill with a figure if there is only one possibility "Bxd5"
                                        //kill with a pawn since you always give a line which indicates which pawn to take "exd5".
                                        //To move a figure if there are 2 possibilities "Bdb8"
                                        //pawn promotion indicated  "e8=Q"
                if (moveKill[0].matches("^[RBNQK]*$") & moveKill[1].matches("^[x]*$") & moveKill[2].matches("^[a-h]*$") & moveKill[3].matches("^[1-8]*$")) {//to kill with a figure check()
                    stringCheck = true;
                    capture = true;
                    input = moveKill[0] + moveKill[2] + moveKill[3]; //input with no x
                }
                if (moveKill[0].matches("^[a-h]*$") & moveKill[1].matches("^[x]*$") & moveKill[2].matches("^[a-h]*$") & moveKill[3].matches("^[1-8]*$")) {//pawn kills someone check()
                    stringCheck = true;
                    capture = true;
                    input = moveKill[0] + moveKill[2] + moveKill[3]; //input with no x
                }
                if (moveKill[0].matches("^[RBNQK]*$") & moveKill[1].matches("^[a-h]*$") & moveKill[2].matches("^[a-h]*$") & moveKill[3].matches("^[1-8]*$")) {//move figure if there are 2 possibilities
                    stringCheck = true;
                }
                if (moveKill[0].matches("^[a-h]*$") & moveKill[1].matches("^[1-8]*$") & moveKill[2].matches("^[=]*$") & moveKill[3].matches("^[RBNQK]*$")) {//check for promotion input
                    stringCheck = true;
                }
            }

            else if (inputLength == 5) { //to kill if 2 figures could kill the same enemy figure "Bdxb8"
                                    //Castling on Queens' side "o-o-o"
                                    //to move with a figure, both x and y since they could reach the same place e.g Queen "Qh4e1"
                if (moveKill[0].matches("^[RBNQK]*$") & moveKill[1].matches("^[a-h]*$") & moveKill[2].matches("^[x]*$") & moveKill[3].matches("^[a-h]*$") & moveKill[4].matches("^[1-8]*$")) {//check for 2 figures input
                    stringCheck = true;
                    capture = true;
                    input = moveKill[0] + moveKill[1] + moveKill[3] + moveKill[4];//change input
                }
                if (moveKill[0].matches("^[o]*$") & moveKill[1].matches("^[-]*$") & moveKill[2].matches("^[o]*$") & moveKill[3].matches("^[-]*$")& moveKill[4].matches("^[o]*$")) {//casteling queenside input
                    stringCheck = true;
                }
                if (moveKill[0].matches("^[RBNQK]*$") & moveKill[1].matches("^[a-h]*$") & moveKill[2].matches("^[a-h]*$") & moveKill[3].matches("^[1-8]*$")& moveKill[4].matches("^[1-8]*$")) {//move when 2 figures are on same x or y line input
                    stringCheck = true;
                }
            }
            else if (inputLength == 6) { //to kill with a figure, both x and y since they are both are on the same line e.g Queen "Qh4xe1"
                if (moveKill[0].matches("^[RBNQK]*$") & moveKill[1].matches("^[a-h]*$") & moveKill[2].matches("^[1-8]*$") & moveKill[3].matches("^[x]*$") & moveKill[4].matches("^[a-h]*$") & moveKill[5].matches("^[1-8]*$")) {//check for 2 figures input
                    stringCheck = true;
                    capture = true;
                    input = moveKill[0] + moveKill[1] + moveKill[2] + moveKill[4] + moveKill[5];//change input
                }
            }
            else if (inputLength == 8){ //make a en passant move "exd6e.p."
                if (moveKill[0].matches("^[a-h]*$") & moveKill[1].matches("^[x]*$") & moveKill[2].matches("^[a-h]*$") & moveKill[3].matches("^[1-8]*$") & moveKill[4].matches("^[e]*$") & moveKill[5].matches("^[.]*$") & moveKill[6].matches("^[p]*$") & moveKill[7].matches("^[.]*$")) {//check for 2 figures input
                    stringCheck = true;
                    capture = true;
                    input = moveKill[0] + moveKill[2] + moveKill[3];
                    //change input that parseInput can make an "en passant"
                }
            }
            else{System.out.println("Invalid Input");//prints if stringCheck is wrong, to let user know that input is wrong
            }
        }
        System.out.println("");
        checkedMove[0] = input;
        checkedMove[1] = capture;
        return checkedMove;
    }

    /**
     * @param checkedInput array Object[(String) input, (Boolean) capture] of already checked player input and information if player intends to do a capture move
     *                     parseInput parses the given input into the coordinates of a) the intended piece to move and b) the wished destination
     * @return array Object[(class) Figure Type, (int) row-coordinate current, (int) column-coordinate current, (int) row-c move, (int) col-c move, (bool) capture]
     */
    private Object[] parseInput(Object[] checkedInput){
        String input = (String)checkedInput[0];
        boolean capture = (Boolean)checkedInput[1];
        int length = input.length();
        Object[] parsedInput = new Object[6];
        parsedInput[5] = capture;

        ArrayList<Character> mapping = new ArrayList<Character>();
        for (char c = 'a'; c <= 'h'; ++c){
            mapping.add(c);
        }

        Map <Character, Class> figureCatalog = new HashMap<Character, Class>();
        figureCatalog.put('R', Rook.class);
        figureCatalog.put('B', Bishop.class);
        figureCatalog.put('N', Knight.class);
        figureCatalog.put('Q', Queen.class);
        figureCatalog.put('K', King.class);

        if (length == 2){
            parsedInput[0] = Pawn.class;
            parsedInput[3] = Character.digit(input.charAt(1),10);
            parsedInput[4] = mapping.indexOf(input.charAt(0));
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

    /**
     * @param gameBoard Copy of the actual game board
     *                  Prints the chessboard with the current location of every piece
     */
    private void printBoard(Object[][][] gameBoard){
        char type;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(gameBoard[i][j][1] != null){
                Figure figure = (Figure)gameBoard[i][j][1];
                char color = figure.getColor().toString().charAt(0);
                if(figure.getClass() == Knight.class){
                    type = 'N';
                }
                else{
                    type = figure.getClass().getName().charAt(0);
                }
                System.out.format("[%s%s]", color, type);
                }
                else{
                    System.out.print("[  ]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param currentPlayer The last player who was playing
     *                      This method changes the current player accordingly to the games course.
     * @return The new current player (e.g. if it was white it's now black and vice versa)
     */
    private Player playersTurn(Player currentPlayer){
        if(currentPlayer.getColor() == Player.colors.WHITE){
            return this.black;
        }
        return this.white;
    }
}

