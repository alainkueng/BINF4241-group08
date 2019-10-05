import java.util.concurrent.ThreadLocalRandom;

public class Ladder extends Square{

    int new_position;

    public Ladder(int number, Game game_obj) {
        super(number, game_obj);
        new_position = move_length() + this.position; //implementation pending, calculates where player will be moved
    }

    @Override
    public void enter(Player player) {
        super.enter(player);
        player.move(new_position);
    }

    public int move_length(){
        int length;
        if (game.board_width == 1 || game.board_height == 1){
            if(game.board_width == 1){
                length = ThreadLocalRandom.current().nextInt(2, game.board_height/3);
            }
            else{
                length = ThreadLocalRandom.current().nextInt(2, game.board_width/3);
            }
        }
        else if(game.board_width >= game.board_height){
            length = game.board_width;
        }
        else{
            length = game.board_height;
        }
        return length;
    }
}
