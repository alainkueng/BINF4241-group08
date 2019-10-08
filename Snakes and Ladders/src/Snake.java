public class Snake extends Ladder {

    int new_position;

    public Snake(int number, Game game_obj, int squarenumber) {
        super(number, game_obj, squarenumber);
        new_position = squarenumber;
    }

    public void settrue(Snake snake){
        snake.partner = true;
    }
}
