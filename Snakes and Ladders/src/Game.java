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
            System.out.println("Player " + i + ":");

            user.setName();
            while (user.getName().equals("") && players.isEmpty()){
                System.out.println("Please input a Name");
                user.setName();

            }
            if(user.getName().equals("") && players.size() < 2){
                System.out.println("One is the lonliest number, but sure play by yourself");
                break;
            }
            else if(user.getName().equals("") && players.size() >= 2){
                System.out.println("No other player entered");
                break;
            }
            players.add(user);

        }
        for(PlayerS player : players){
            System.out.println(player.getName());
        }
    }

    public int turn(){
        this.current += 1 % 4;
        return this.current;
    }

    //have an int current that keeps track of whos turn it is to play, USE public int turn as i
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
//    public boolean islastsquare(){
//        return true;
//    }

}