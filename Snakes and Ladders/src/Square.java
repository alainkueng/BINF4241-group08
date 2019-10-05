import java.util.ArrayList;
import java.util.List;

public class Square{
        List<Player> player_list = new ArrayList<Player>();
        Game game;
        int position;

        public Square(int number, Game game_obj){
            position = number;
            game = game_obj;
        }

    public void enter(Player player) {
        this.player_list.add(player);
    }

    public void leave(Player player_leaves){
            if(this.player_list.get(0).name == player_leaves.name){
                this.player_list.remove(0);
            }
            else{
                System.out.println("You want to move a player which is not on this field");
            }
    }

    public void moveAndLand(int to_move){
            findSquare(to_move);
    }

    private Square findSquare(int to_move){
            Square new_Square = game.get_square(to_move, this);
            if (new_Square.isOccupied()){
                return findSquare(1);
            }
            else{
                return new_Square;
            }
    }

        public boolean isOccupied() {
            if (this.player_list.get(0) == null) {
                return true;
            } else {
                return false;
            }
        }
}