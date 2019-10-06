import java.util.ArrayList;
import java.util.List;

public class Square{
        List<Player> player_list = new ArrayList<Player>();//keeps track of which players are on this square
        Game game; //is needed to have access to game methods like get_square()
        int position; //square's position
        boolean last; //shows if this square is the last

        // constructor
        public Square(int number, Game game_obj){
            position = number;
            game = game_obj;
            last = false;
        }

    public void enter(Player player) {
        /**
         * @param Player player: The player who enters the field
         *               This method adds the player which enters the field to the player_list
         */
        this.player_list.add(player);
    }

    public void leave(Player player_leaves){
        /**
         * @param Player player_leaves: The player who leaves the field
         *               Checks if the player who wants to leave is on this field and then removes it from player_list
         */
        if(this.player_list.get(0).name == player_leaves.name){
                this.player_list.remove(0);
            }
            else{
                System.out.println("You want to move a player which is not on this field");
            }
    }

    public Square moveAndLand(int to_move){
            /**
             * @param int to_move: Number of fields a player wants to move
             *            invokes findSquare() to check to which square player will move and if it's not occupied
             * @return Square: Square on which the player will be placed next
             */
            return findSquare(to_move);
    }

    private Square findSquare(int to_move){
        /**
         * @param int to_move: Number of fields a player wants to move
         *            invokes game.get_square() to get square where player wants to move
         *            invokes then isOccupied() to check if square isn't occupied with another player
         * @return Square next_square: The square the player will have to move
         */
        Square new_square = game.get_square(to_move, this);
        Square next_square;
            if (new_square.isOccupied()){
                next_square = findSquare(1);
            }
            else{
                next_square = new_square;
            }
            return next_square;
    }

        public boolean isOccupied() {
            /**
             * Checks if this square has a player on it and returns true if so, and false otherwise
             * @return boolean
             */
            if (this.player_list == null) {
                return true;
            } else {
                return false;
            }
        }
}