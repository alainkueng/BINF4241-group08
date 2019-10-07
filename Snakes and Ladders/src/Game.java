import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    private Player winner;
    public int current;
    public int board_width;
    public int board_height;
    public int board_size;
    int numPlayer;
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
            Player current_player = players.get(this.current);
            int to_move = checkNumber(random_number, current_player);
            current_player.move(to_move);
            checkLast(current_player);
            // muss noch display einbauen für jeden move
            nextPlayer();
            //
        }
    }
    // create Board with squares
    public void createBoard(){
        System.out.println("Please input height: ");
        Scanner s = new Scanner(System.in);
        while (!s.hasNextInt()) {
            System.out.println("Input is not a number. Retry");
            s.nextLine();}
        String h_size = s.nextLine();
        this.board_height = Integer.parseInt(h_size);  // for board_size
        System.out.println("Please input width: ");
        while (!s.hasNextInt()) {
            System.out.println("Input is not a number. Retry");
            s.nextLine();}
        String w_size = s.nextLine();
        this.board_width = Integer.parseInt(w_size);
        board_size = this.board_height * this.board_width;
        Square square = new FirstSquare(1, this);
        squares.add(square);
        for (int i = 2; i < board_size; i++) {
            square = new Square(i, this);
            squares.add(square); // creates Squares in list
        }
        square = new LastSquare(board_size,this);
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
            while (user.getName().equals("") && players.isEmpty()){
                System.out.println("Please input a Name");
                user.setName(i);

            }
            if(numPlayer < 2){
                System.out.println("One is the loneliest number, but sure play by yourself");
            }
            players.add(user);
            user.square.enter(user);
        }
//        for(PlayerS player : players){
//            System.out.println(player.getName());
//        }
        System.out.println("\nGame starting now!");

    }


    public Square getSquare(int square_number){
        return squares.get(square_number - 1);
    }

    //keeps track of whose turn it is
    public void nextPlayer(){
        this.current = (this.current += 1) % current;
    }
    public void checkLast(Player current_player){
        if (current_player.square.last){
            this.winner = current_player;
            System.out.format("Seems like %s just won the game.", current_player.name);
        }
    }
    public int checkNumber(int to_move, Player current_player){
        int current_position = current_player.square.position;
        while (to_move + current_position > board_size || to_move + current_position <= 1 || current_position == board_size){
            if (to_move + current_position > board_size){
                to_move = (to_move - (board_size - current_position))*-1;
                current_position = board_size;
            }
            else if (to_move == 0){
                break;
            }

            else{
                if(to_move + board_size < 1){
                    current_position = 1;//crt_pos to 1st square to count again from there
                    to_move = (to_move*-1) - (board_size - current_position);
                 }
                else{
                    to_move = to_move + current_player.square.position;
                }
            }
        }
        return to_move;
    }
}