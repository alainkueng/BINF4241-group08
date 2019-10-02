import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {



    public List<PlayerS> players;



    //Constructor
    Game(){
        this.players = new ArrayList<PlayerS>(4);
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
                System.out.println("One is the lonliest number");
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

//    public static void remove(){
//
//    }
//
//    public static Square whatsquare(){
//        return;
//    }
//
//    public boolean islastsquare(){
//        return true;
//    }

}