public class Snake extends Ladder {
    public Snake(int number, Game game_obj) {
        super(number, game_obj);
        new_position = this.position - move_length();
    }
}
