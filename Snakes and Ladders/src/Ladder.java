import java.util.concurrent.ThreadLocalRandom;

public class Ladder extends Square {

    int new_position;

    public Ladder(int number, Game game_obj, int squarepos) {
        super(number, game_obj);
        new_position = squarepos;}

    @Override
    public void enter(Player player) {
        super.enter(player);
        player.move(this.position-new_position);

    }
    public void settrue(Ladder ladder){
        ladder.partner = true;
    }
}


//    public int move_length(){
//        int length;
//        if (game.board_width == 1 || game.board_height == 1){
//            if(game.board_width == 1){
//                length = ThreadLocalRandom.current().nextInt(2, game.board_height/3);
//            }
//            else{
//                length = ThreadLocalRandom.current().nextInt(2, game.board_width/3);
//            }
//        }
//        else if(game.board_width >= game.board_height){
//            length = game.board_width;
//        }
//        else{
//            length = game.board_height;
//        }
//        return length;
//    }
//}
