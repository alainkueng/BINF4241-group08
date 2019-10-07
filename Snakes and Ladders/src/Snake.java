public class Snake extends Ladder {

    int new_position;

    public Snake(int number, Game game_obj) {
        super(number, game_obj);
        new_position = this.position - move_length();
    }


    public int getSnakePosition(){
        return new_position;
    }
}
