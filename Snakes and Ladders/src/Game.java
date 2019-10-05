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
        addplayer(); // anzahl spieler vorher herausfinden wie auf Blatt beschrieben?
        Dice dice = new Dice();
        while (this.winner==false){
            int randomnumber = dice.dice();
            Player Currentplayer= players.get(this.current);
            Currentplayer.move(randomnumber);
            // muss noch display einbauen für jeden move
            next();
        }




    }
    // create Board with squares
    public void createboard(){
        System.out.println("Please input number of fields: ");
        Scanner s = new Scanner(System.in);
        String size = s.nextLine();
        int boardsize = Integer.valueOf(size);  // reads boardsize
        Square square = new FirstSquare(1, this);
        squares.add(square);
        for (int i = 2; i < boardsize; i++){
            square = new Square(i, this);
            squares.add(square); // creates Squares in list
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

    }

    //keeps track of whos turn it is
    public int next(){
        this.current += 1 % 4;
        return this.current;
    }
    public void getfirstsquare(){

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