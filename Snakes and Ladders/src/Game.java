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
        for (int i = 0; i <= 4; i++) {
            PlayerS user = new PlayerS();
            user.setName();
            players.add(i,user);
        }
        System.out.println(players);
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