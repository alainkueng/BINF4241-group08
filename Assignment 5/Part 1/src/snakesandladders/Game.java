package snakesandladders;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;



public class Game {

    private Player winner;
    public int current;
    public int board_width;
    public int board_height;
    public int board_size;
    public int numPlayer;
    public List<Player> players;
    public List<Square> squares;
    public String Players;


    //Constructor
    Game() {

        this.players = new ArrayList<Player>(4);
        this.winner = null;
        this.current = 0;
        this.squares = new ArrayList<Square>();
    }

    public void run(){
        boolean checkWidthSize = false;
        boolean checkHeightSize = false;
        boolean boardSize = false;
        while(!boardSize) {
            while (!checkWidthSize) {
                System.out.println("Please enter board width: ");
                String width = readInput();
                if (checkNumberStringForCreateBoard(width)) {
                    this.board_width = Integer.parseInt(width);
                    checkWidthSize = true;
                }
            }
            while (!checkHeightSize) {
                System.out.println("Please enter board height: ");
                String height = readInput();
                if (checkNumberStringForCreateBoard(height)) {
                    this.board_height = Integer.parseInt(height);
                    checkHeightSize = true;
                }
            }
            if (createBoard(board_height, board_width)){
                boardSize = true;
            }
            else{
                System.out.println("Sorry but there is something wrong with the board. " +
                        "It has to be more than 1 and less than 10000. Please retry");
                checkHeightSize = false;
                checkWidthSize = false;
            }
        }
        boolean checkNumber = false;
        while(!checkNumber){
            System.out.println("Please enter how many players:");
            checkNumber = checkNumberStringForAddPlayer(readInput());
        }
        for (int i = 1; i<= numPlayer; i++) {
                boolean checkName = false;
                boolean checkExisting = false;
                while (!checkName & !checkExisting) {
                    System.out.printf("Please enter player name for player %d\n", i);
                    String checkExist = readInput();
                    if(checkPlayerString(checkExist)&& compareNames(checkExist)){
                        checkExisting = true;
                        Players = checkExist;
                        checkName = true;
                    }

                }
                addPlayerToBoard(Players);
        }
        System.out.println("\nGame starting now!");
        System.out.print("Initial state:\t");
        printGame();
        Dice dice = new Dice();
        while (this.winner == null) {
            int random_number = dice.dice();
            Player current_player = players.get(this.current);
            System.out.format("%s rolls %d:\t", current_player.name, random_number);
            int to_move = checkNumber(random_number, current_player);
            printGame();
            current_player.move(to_move);


            checkLast(current_player);
            nextPlayer();
        }

    }
    public boolean checkNumberStringForCreateBoard(String numberInput){
        if ( numberInput == null) {
            System.out.println("Sorry but this input is invalid, please enter a board size that isn't greater than 10000");
            return false;
        }
        int length = numberInput.length();
        if (length == 0) {
            System.out.println("Sorry but this input is invalid, please enter a board size that isn't greater than 10000");
            return false;
        }
        int i = 0;
        if (numberInput.charAt(0) == '-') {
            if (length == 1) {
                System.out.println("Sorry but this input is invalid, please enter a board size that isn't greater than 10000");
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = numberInput.charAt(i);
            if (c < '0' || c > '9') {
                System.out.println("Sorry but this input is invalid, please enter a board size that isn't greater than 10000");
                return false;
            }
        }
        return true;
    }

    // create Board with squares
    public boolean createBoard(int height, int width) {
        board_size = height*width;
        if(board_size>10001 || board_size < 2){
            return false;
        }
        Square square = new FirstSquare(1, this);
        squares.add(square);
        for (int i = 2; i < board_size; i++) {
            square = new Square(i, this);
            squares.add(square); // creates Squares in list
        }
        square = new LastSquare(board_size, this);
        squares.add(square);
        if (board_size >= 6) {
            int special_number = board_size / 4;
            int snakes_number = special_number / 2;
            int ladders_number = special_number / 2;
            int max_length = board_size/3;
            for (int i = 0; i < snakes_number; i++) {
                int random_position = ThreadLocalRandom.current().nextInt(2, board_size - 1);
                int random_partner = ThreadLocalRandom.current().nextInt(1, random_position);
                Square square_to_transform = squares.get(random_position);
                Square new_partner = squares.get(random_partner);
                int iterations = 0;
                while ((square_to_transform.partner || new_partner.partner || Math.abs(square_to_transform.position-new_partner.position) > max_length) && iterations < board_size) {
                    iterations = 0;
                    while (square_to_transform.partner) {
                        random_position = ThreadLocalRandom.current().nextInt(2, board_size - 1);
                        square_to_transform = squares.get(random_position);
                        random_partner = ThreadLocalRandom.current().nextInt(1, random_position);
                        new_partner = squares.get(random_partner);
                    }
                    while ((iterations < board_size && new_partner.partner) || Math.abs(square_to_transform.position-new_partner.position) > max_length) {
                        random_partner = ThreadLocalRandom.current().nextInt(1, random_position );
                        new_partner = squares.get(random_partner);
                        iterations++;
                    }
                }
                if(iterations == board_size){
                    continue;
                }
                Snake snake = new Snake(random_position + 1, this, random_partner + 1);
                squares.set(random_position, snake);
                squares.get(snake.new_position-1).partner = true;
            }
            for (int i = 0; i < ladders_number; i++) {
                int random_position = ThreadLocalRandom.current().nextInt(1, board_size - 3);
                int random_partner = ThreadLocalRandom.current().nextInt(random_position + 1, board_size - 2);
                Square square_to_transform = squares.get(random_position);
                Square new_partner = squares.get(random_partner);
                int iterations = 0;
                while ((square_to_transform.partner || new_partner.partner || Math.abs(square_to_transform.position-new_partner.position) > max_length)&& iterations < board_size) {
                    iterations = 0;
                    while (square_to_transform.partner) {
                        random_position = ThreadLocalRandom.current().nextInt(1, board_size - 3);
                        square_to_transform = squares.get(random_position);
                        random_partner = ThreadLocalRandom.current().nextInt(random_position + 1, board_size - 2);
                        new_partner = squares.get(random_partner);
                    }
                    while ((iterations < board_size && new_partner.partner) || Math.abs(square_to_transform.position-new_partner.position) > max_length) {
                        random_partner = ThreadLocalRandom.current().nextInt(random_position + 1, board_size - 2);
                        new_partner = squares.get(random_partner);
                        iterations++;
                    }
                }
                if(iterations == board_size){
                    continue;
                }
                Ladder ladder = new Ladder(random_position + 1, this, random_partner + 1);
                squares.set(random_position, ladder);
                squares.get(ladder.new_position-1).partner = true;
            }

        }
        return true;
    }
    public String readInput(){
        Scanner stringInput = new Scanner(System.in);
        String input = stringInput.next();
        return input;
    }

    public boolean checkPlayerString(String playerName){
        if(playerName.matches("[A-Za-z ]+") && playerName.length() < 16 && playerName.length() > 0 && !playerName.matches("[ ]+")){
            return true;
        }
        else{
            System.out.println( "I'm sorry but this name is not valid. " +
                                "Only alphabet letters and the name length should be smaller than 16");
            return false;
        }
    }



    public boolean checkNumberStringForAddPlayer(String numberPlayers){
        if (numberPlayers.matches("[1-4]") && numberPlayers.length() == 1){
            numPlayer = Integer.parseInt(numberPlayers);
            return true;
        }
        else{
            System.out.println("I'm sorry but this isn't a valid Input. Max 4 Players.");
            return false;
        }
    }


    public void addPlayerToBoard(String playerName) {
        Player user = new Player(squares.get(0));
        user.setName(playerName);
        players.add(user);
        user.square.enter(user);
    }


    public boolean compareNames(String playerName){
        for (Player Player: this.players){
            if (Player.getName().equals(playerName)){
                System.out.println("Sorry but this name is already taken");
                return false;
            }
        }
        return true;
    }

    public Square getSquare(int square_number) {
        return squares.get(square_number - 1);
    }

    //keeps track of whose turn it is
    public void nextPlayer() {
        this.current = (this.current += 1) % numPlayer;
    }

    public void checkLast(Player current_player) {
        if (current_player.square.last) {
            this.winner = current_player;
            System.out.print("Final state:\t");
            printGame();
            System.out.format("%s wins!", current_player.name);
        }
    }

    public int checkNumber(int to_move, Player current_player) {
        int current_position = current_player.square.position;

        if (to_move + current_position > board_size) {
            int offset = (current_position + to_move) % board_size;
            to_move = (board_size - offset) - current_position;

        }
        return to_move;

    }

        public void printGame() {
            for (int i = 0; i < squares.size(); i++) {
                Square current_square = squares.get(i);
                List current_player_list = squares.get(i).player_list;
                if (current_player_list.size() != 0) {
                    System.out.print("[" + (i + 1));
                    for (int j = 0; j < current_player_list.size(); j++) {
                            System.out.print("<" + current_square.player_list.get(j).name + ">");
                    }
                    System.out.print("]");
                    continue;
                }
                else if(current_square.getClass() == Snake.class){
                    Snake snake = (Snake) squares.get(i);
                    System.out.print("[" + snake.new_position + "<-" + (i + 1) + "]");
                    continue;
                }
                else if(current_square.getClass() == Ladder.class){
                    Ladder ladder = (Ladder) squares.get(i);
                    System.out.print("[" + (i+1) + "->" + ladder.new_position + "]" );
                    continue;
                }
                    System.out.print("[" + (i + 1) + "]");
            }
            System.out.println("");
        }
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}




