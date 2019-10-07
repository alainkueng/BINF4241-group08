import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    private Player winner;
    public int current;
    public int board_width;
    public int board_height;
    public int board_size;
    public int numPlayer;
    public List<Player> players;
    public List<Square> squares;


    //Constructor
    Game() {

        this.players = new ArrayList<Player>(4);
        this.winner = null;
        this.current = 0;
        this.squares = new ArrayList<Square>();
        createBoard();
        addPlayer(); // anzahl spieler vorher herausfinden wie auf Blatt beschrieben?
        Dice dice = new Dice();
        while (this.winner == null) {
            int random_number = dice.dice();
            players.get(this.current);
            int to_move = checkNumber(random_number, players.get(this.current));
            players.get(this.current).move(to_move);
            checkLast(players.get(this.current));
//            printGame();
            nextPlayer();
        }
    }

    // create Board with squares
    public void createBoard() {
        System.out.println("Please input height: ");
        Scanner s = new Scanner(System.in);
        while (!s.hasNextInt()) {
            System.out.println("Input is not a number. Retry");
            s.nextLine();
        }
        String h_size = s.nextLine();
        this.board_height = Integer.parseInt(h_size);  // for board_size
        System.out.println("Please input width: ");
        while (!s.hasNextInt()) {
            System.out.println("Input is not a number. Retry");
            s.nextLine();
        }
        String w_size = s.nextLine();
        this.board_width = Integer.parseInt(w_size);
        board_size = this.board_height * this.board_width;
        Square square = new FirstSquare(1, this);
        squares.add(square);
        for (int i = 1; i < board_size - 1; i++) {
            square = new Square(i, this);
            squares.add(square); // creates Squares in list
        }
        square = new LastSquare(board_size, this);
        squares.add(square);
    }

    //add players to the board + muss noch jedem player den ersten Square zuteilen
    public void addPlayer() {

        Scanner numPlayers = new Scanner(System.in);
        System.out.println("Number of players?");
        numPlayer = numPlayers.nextInt();
        for (int i = 1; i <= numPlayer; i++) {
            Player user = new Player(squares.get(0));
            user.setName(i);
            while (user.getName().equals("") && players.isEmpty()) {
                System.out.println("Please input a Name");
                user.setName(i);

            }
            if (numPlayer < 2) {
                System.out.println("One is the loneliest number, but sure play by yourself");
            }
            players.add(user);
            user.square.enter(user);
        }
        System.out.println("\nGame starting now!");

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
            System.out.format("Seems like %s just won the game.", current_player.name);
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


//    public void addSnakesAndLadders(){
//        int i = 0;
//        int j = 0;
//        Snake snake = new Snake(i, );
//        Ladder ladder = new Ladder(j,);
//    }

        public void printGame() {
            for (int i = 0; i < squares.size(); i++) {
                //square 1
                if (!squares.get(i).isOccupied()) {
                    System.out.print(" [" + (i + 1));
                    for (int j = 0; j < squares.get(i).player_list.size(); j++) {
                        System.out.print(" <" + squares.get(i).player_list.get(j).getName() + ">");
                    }
                    System.out.print("] ");
                    System.out.println(" ");
                } else if (squares.get(i).isOccupied()) {
                    System.out.print(" [" + (i + 1) + "] ");
                } else {
                    break;
                }
            }
        }
//            else if(!squares.get(i).isOccupied() && !squares.get(i).hasSnake && !squares.get(i).hasLadder){
//                System.out.println(" [" + i + "] ");
//
//                //is empty but has a snake
//            } else if (!squares.get(i).isOccupied() && squares.get(i).hasSnake){
//                System.out.println(" [" + i + " <- " + "Snake get position" + "]");
//            }
//            // is empty but has ladder
//            else if(!squares.get(i).isOccupied() && squares.get(i).hasLadder){
//                System.out.println(" [" + i + " -> " + "Ladder.getnewPosition" + "]");
//            }
//
//            else if(squares.get(i).isOccupied()){
//                System.out.println(" [" + i + "< " + squares.get(i).player_list.get(i).getName() + " >" + "]");
//            }
//

        }




