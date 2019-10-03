import java.util.ArrayList;
import java.util.List;


public class Game {

    private boolean winner;
    public int current;
    public List<PlayerS> players;


    //Constructor
    Game(){

        this.players = new ArrayList<PlayerS>(4);
        this.winner = false;
        this.current = 0;
    }

    //add players to the board
    public void addplayer() {
        for (int i = 1; i <= 4; i++) {
            PlayerS user = new PlayerS();

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