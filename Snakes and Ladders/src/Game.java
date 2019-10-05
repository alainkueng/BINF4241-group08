import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    private boolean winner;
    public int current;
    public List<Player> players;
    public List<Square> squares;


    //Constructor
    Game(){

        this.players = new ArrayList<Player>(4);
        this.winner = false;
        this.current = 0;
        this.squares = new ArrayList<Square>();
        createboard();
        addplayer();
    }
    // create Board with squares
    public void createboard(){
        System.out.println("Please input boardsize: ");
        Scanner s = new Scanner(System.in);
        String size = s.nextLine();
        int boardsize = Integer.valueOf(size);  // reads boardsize
        for (int i = 1; i <= boardsize; i++){
            Square square = new Square(i);
            squares.add(square); // creates Squares in list
        }
    }

    //add players to the board + muss noch jedem player den ersten Square zuteilen
    public void addplayer() {
        for (int i = 1; i <= 4; i++) {
            Player user = new Player(squares.get(1));
            user.setName(i);
            while (user.getName().equals("") && players.isEmpty()){
                System.out.println("Please input a Name");
                user.setName(i);

            }
            if(user.getName().equals("") && players.size() < 2){
                System.out.println("One is the loneliest number, but sure play by yourself");
                break;
            }
            else if(user.getName().equals("") && players.size() >= 2){
                System.out.println("No other was player entered");
                break;
            }
            players.add(user);
        }
//        for(PlayerS player : players){
//            System.out.println(player.getName());
//        }
        System.out.println("\nGame starting now!");

    }


    //keeps track of whos turn it is
    public int next(){
        this.current += 1 % 4;
        return this.current;
    }

    public void remove(int i){
        //delete from board
        players.get(i).pos = -1;

        //delete from player list
        players.remove(i);
        //TODO: Remove from gameboard/square
    }
    public Square getfirstsquare(){

    }

//
//    return position
//    public int whatsquare(){
//        return players.get(turn()).pos;
//    }
//
//    public boolean islastsquare(Square s){
//        return true;
//    }

}